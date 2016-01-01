package week2;

import java.util.ArrayList;

/**
 * Sorting Quake data.
 */


public class QuakeSortInPlace
{
    public QuakeSortInPlace() {
        
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public  ArrayList<QuakeEntry> getQuakeData() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/quakedata/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        return parser.read(source); 
    }

    public  ArrayList<QuakeEntry> getQuakeData(String fname) {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/quakedata/"+fname;
        return parser.read(source); 
    }
    
    public void testSort() {

        ArrayList<QuakeEntry> list  = getQuakeData();  
       
        System.out.println("read data for "+list.size()+" quakes");    
        sortByMagnitude(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV(){
        
        ArrayList<QuakeEntry> list  = getQuakeData();
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
    
    // Assignment 1: Sort by Depth 
    
    /**
     * This method returns an integer representing the index position of the 
     * QuakeEntry with the largest depth considering only those QuakeEntry’s 
     * from position from to the end of the ArrayList. 
     * @param quakeData
     * @param from	integer representing an index position in the ArrayList
     * @return
     */
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
    	
    	int indDeepest = from;
    	for (int i =from+1; i< quakeData.size();i++) {
    		
    		double deepestSoFar = quakeData.get(indDeepest).getDepth();
    		double depth = quakeData.get(i).getDepth();
    		
    		if (depth<deepestSoFar) indDeepest=i;
    		
    	}
    	
    	return indDeepest;
    	
    } 
    
    /**
     * This method sorts the QuakeEntry’s in the ArrayList by depth using the 
     * selection sort algorithm, but in reverse order from largest depth to 
     * smallest depth (the QuakeEntry with the largest depth should be in 
     * the 0th position in the ArrayList).
     * @param quakeData
     */
    public void sortByLargestDepth(ArrayList<QuakeEntry> quakeData) {
    	
    	// for the quiz, set i<50
//    	int limit = quakeData.size();
    	int limit = 50;
    	for (int i=0; i<limit; i++) {
    		int indDeepest = getLargestDepth(quakeData, i);
    		QuakeEntry qi = quakeData.get(i);
    		QuakeEntry qDeepest = quakeData.get(indDeepest);
    		quakeData.set(i, qDeepest);
    		quakeData.set(indDeepest, qi);
    	}
    }
    
    /**
     * Testing Assignment 1: Sort by Depth 
     */
    public void testSortByLargestDepth() {

        ArrayList<QuakeEntry> list  = getQuakeData("earthQuakeDataDec6sample2.atom");  
       
        System.out.println("read data for "+list.size()+" quakes");    
        sortByLargestDepth(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
    }
    
    // Assignment 2: Bubble Sort
    
    /**
     * Sorting quakes by magnitude.
     * This method makes one pass of bubble sort on the ArrayList. 
     * @param quakeData
     * @param numSorted	number of times this method has already been called on 
     * this ArrayList
     */
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData
    		, int numSorted) {
    
    	for (int i=0; i<quakeData.size()-1-numSorted;i++) {
    		QuakeEntry q1 = quakeData.get(i);
    		QuakeEntry q2 = quakeData.get(i+1);
    		
    		if (q1.getMagnitude()>q2.getMagnitude()) {
    			quakeData.set(i, q2);
    			quakeData.set(i+1,q1);
    		}
    	}
    	
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
    	for (int i=0; i<in.size();i++) 
    		onePassBubbleSort(in, i);
    }
    /**
     * Testing Assignment 2: Bubble Sort.
     */
    public void testSortByMagnitudeWithBubbleSort() {
    	ArrayList<QuakeEntry> list = getQuakeData();
    	System.out.println("read data for "+list.size()+" quakes"); 
    	sortByMagnitudeWithBubbleSort(list);
    	
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
    	
    }
    
    // Assignment 3: Check for Completion
    
    /**
     * Check if quakes list is sorted by magnitude in ascending order.
     * @param quakes
     * @return
     */
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
    	
    	for (int i=0;i<quakes.size()-1;i++) {
    		if (quakes.get(i).getMagnitude() > 
    				quakes.get(i+1).getMagnitude()
    				) return false;
    	}
    	
    	return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
    	for (int i=0; i<in.size();i++) {
    		if (checkInSortedOrder(in)) {
    			System.out.printf("Number of passes: %d\n", i);
    			break;
    		}
    		onePassBubbleSort(in, i);
    	}
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
    	
        for (int i=0; i< in.size(); i++) {
        	
    		if (checkInSortedOrder(in)) {
    			System.out.printf("Number of passes: %d\n", i);
    			break;
    		}
        	
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    	
    	
    }
    
    
    /**
     * Testing Assignment 3: Check for Completion
     */
    public void testSortByMagnitudeWithBubbleSortWithCheck() {
    	ArrayList<QuakeEntry> sorted1 = getQuakeData("earthQuakeDataWeekDec6sample2.atom");
//    	ArrayList<QuakeEntry> sorted2 = getQuakeData("earthquakeDataSampleSix2.atom");
    	sortByMagnitudeWithBubbleSortWithCheck(sorted1);
//    	sortByMagnitudeWithBubbleSortWithCheck(sorted2);
    	
    }
    
    public void testSortByMagnitudeWithCheck() {
    	ArrayList<QuakeEntry> sorted1 = getQuakeData("earthQuakeDataWeekDec6sample2.atom");
//    	ArrayList<QuakeEntry> sorted2 = getQuakeData("earthquakeDataSampleSix2.atom");
    	sortByMagnitudeWithCheck(sorted1);
//    	sortByMagnitudeWithCheck(sorted2);
    	
    }
    
    public static void main(String[] args) {
    	QuakeSortInPlace qs = new QuakeSortInPlace();
//		qs.testSortByLargestDepth();
//    	qs.testSortByMagnitudeWithCheck();
//    	qs.testSortByMagnitudeWithBubbleSort();
    	qs.testSortByMagnitudeWithBubbleSortWithCheck();
    	
    }
}
