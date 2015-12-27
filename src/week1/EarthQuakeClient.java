package week1;

import java.util.*;
import java.util.regex.Pattern;

public class EarthQuakeClient
{

    public EarthQuakeClient() {
        
    }

    /**
     * 
     * @param quakeData
     * @param where	where to look for a phrase: 
     * "start" match start of quake description
     * "end" match end of quake description
     * any other parameter matches any occurrence of string
     * @param phrase phrase to be looked up
     * @return	list of quakes matching the filter
     */
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    		String where, String phrase) {
    	
    	Pattern p;
    	
    	switch (where) {
    	
    	// match start of string
		case "start":
			p = Pattern.compile("^"+phrase);
			break;
		
		// match end of string
		case "end":
			p = Pattern.compile(phrase+"$");
			break;

		// match any
		default:
			p = Pattern.compile(phrase);
			break;
		}
    	
    	ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
    	for (QuakeEntry qe : quakeData) {
    		if (p.matcher(qe.getInfo()).find())  
    			ret.add(qe);
    	}
    	
    	return ret;
    }
    
    public void quakesByPhrase() {
    	
    	ArrayList<QuakeEntry> list = getQuakeData();
    	System.out.println("read data for "+list.size()+" quakes");
    	
    	ArrayList<QuakeEntry> matches = filterByPhrase(list, "any", "Can");
    	
    	
    	for (QuakeEntry qe : matches) {
    		System.out.println(qe.getInfo());
    	}
    	
    	System.out.printf("Found %d quakes that match that criteria\n",matches.size());
    }
    
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    		double minDepth, double maxDepth) {
    	ArrayList<QuakeEntry> ans = new ArrayList<QuakeEntry>();
    	
    	for (QuakeEntry qe : quakeData) {
    		double depth = qe.getDepth();
    		if (depth > minDepth && depth < maxDepth ) 
    			ans.add(qe);
    	}
    	
    	return ans;
    }
    
    public void quakesOfDepth() {
    	
    	double min = -4000.0;
    	double max =  -2000.0;
    	
    	ArrayList<QuakeEntry> quakeData = getQuakeData();
    	
    	System.out.println("read data for "+quakeData.size()+" quakes");
    	ArrayList<QuakeEntry> deep = filterByDepth(quakeData, min, max);
    	
    	for (QuakeEntry qe : deep) {
    		System.out.println(qe);
    	}
    	
    	System.out.printf("Found %d quakes that match that criteria\n",deep.size());
    	
    }
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
        	if (qe.getMagnitude() > magMin) answer.add(qe);
        }
        
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
        	double dist = from.distanceTo(qe.getLocation());
        	if (dist < distMax) answer.add(qe);
        }
        
        return answer;
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

    public void bigQuakes() {

        ArrayList<QuakeEntry> list  = getQuakeData();
       
        ArrayList<QuakeEntry> bigQuakes = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : bigQuakes) {
        	System.out.println(qe);
        }
        System.out.printf("Found %d quakes that match that criteria\n",bigQuakes.size());
        
    }

    public void closeToMe(){
 
        ArrayList<QuakeEntry> list  = getQuakeData();
        System.out.println("read data for "+list.size()+" quakes");
        

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000000, city);
        
        for (QuakeEntry qe : close) {
        	double dist = city.distanceTo(qe.getLocation())/1000;
        	System.out.printf("%4.2f %s\n",
        			dist, qe.getInfo());
        	
        }
        
        System.out.printf("Found %d quakes that match that criteria.\n"
        		, close.size());
        

    }

    public ArrayList<QuakeEntry> getQuakeData() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/quakedata/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        return parser.read(source);
    	
    }
    
    public void createCSV(){

    	ArrayList<QuakeEntry> list =getQuakeData(); 
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
        
    }
    
    public static void main(String[] args) {
    	EarthQuakeClient client = new EarthQuakeClient();
    	
    	client.bigQuakes();
//    	client.closeToMe();
//    	client.quakesOfDepth();
//    	client.quakesByPhrase();
    	// Filtered by magnitude
//    	ArrayList<QuakeEntry> filtered = client.filterByMagnitude(quakeData, 5.0);
//    	
//    	client.dumpCSV(filtered);
	}
}
