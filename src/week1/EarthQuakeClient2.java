package week1;

import java.util.*;

public class EarthQuakeClient2
{
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        return answer;
    } 

    public void testMatchAllFilter() {
    	
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/quakedata/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(1.0, 4.0));
        maf.addFilter(new DepthFilter( -180000.0, -30000.0));
        maf.addFilter(new PhraseFilter("any", "o"));
        
        ArrayList<QuakeEntry> filtered = filter(list, maf);
        
        for (QuakeEntry qe : filtered) System.out.println(qe);
        
        System.out.println(maf.getName());
        System.out.printf("Found %d matching.", filtered.size());
    	
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/quakedata/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0));
        
        Location billund = new Location(55.7308, 9.1153);
        maf.addFilter(new DistanceFilter(billund, 3000000));
        maf.addFilter(new PhraseFilter("any", "e"));
        
        ArrayList<QuakeEntry> filtered = filter(list, maf);
        
        for (QuakeEntry qe : filtered) System.out.println(qe);
        
        System.out.println(maf.getName());
        System.out.printf("Found %d matching.", filtered.size());
    }
    
    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/quakedata/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        
        Filter mag = new MagnitudeFilter(3.5, 4.5);
        Filter dep = new DepthFilter(-55000.0, -20000.0);
        
        
        EarthQuakeClient2 eqc = new EarthQuakeClient2(); 
        ArrayList<QuakeEntry> magQuakes = eqc.filter(list, mag);
        ArrayList<QuakeEntry> depQuakes = eqc.filter(magQuakes, dep);
        

        for (QuakeEntry qe: depQuakes) { 
            System.out.println(qe);
        } 
        System.out.printf("Found %d matching.", depQuakes.size());
        
//        Location denver = new Location(39.7392, -104.9903);
//        Filter denverFilter = new DistanceFilter(denver, 1000000);
//        Filter phraseFilter = new PhraseFilter("end", "a");
//        
//        EarthQuakeClient2 eqc = new EarthQuakeClient2();
//        ArrayList<QuakeEntry> denverQuakes = eqc.filter(list, denverFilter);
//        ArrayList<QuakeEntry> finalQuakes = eqc.filter(denverQuakes, phraseFilter);
//        for (QuakeEntry qe : finalQuakes) {
//        	System.out.println(qe);
//        }
//        System.out.printf("Found %d matching.", finalQuakes.size());
        
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/quakedata/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }
    
    public static void main(String[] args) {
		
    	EarthQuakeClient2 client = new EarthQuakeClient2();
//    	client.quakesWithFilter();
//    	client.testMatchAllFilter();
    	client.testMatchAllFilter2();
    	
	}

}
