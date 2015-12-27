package week1;


/***
 *  Determine the N biggest earthquakes, those with 
 *  largest magnitude.
 */
import java.util.ArrayList;

public class LargestQuakes {

	public void findLargestQuakes() {
		
		  EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/quakedata/nov20quakedata.atom";
//	        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
	        
	        ArrayList<QuakeEntry> list  = parser.read(source);
	        System.out.println("read data for "+list.size());
	        
	        ArrayList<QuakeEntry> largest = getLargest(list, 50);
	        
	        for (QuakeEntry qe : largest) 
	        	System.out.println(qe);
	        
	}
	
	private ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,
			int howMany) {
		
		ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
		ArrayList<QuakeEntry> largest = new ArrayList<QuakeEntry>();
		
		for (int i = 0; i<howMany; i++)
			largest.add(copy.remove(indexOfLargest(copy)));
		
		return largest;
	}
	
	/**
	 * This method returns an integer representing the index location in data 
	 * of the earthquake with the largest magnitude.
	 * @param data
	 * @return
	 */
	private int indexOfLargest(ArrayList<QuakeEntry> data) {
		
		// Index of largest found quake so far
		int idx = 0;
		
		for (QuakeEntry qe : data) {
			double mag = qe.getMagnitude();
			if (mag > data.get(idx).getMagnitude()) 
				idx=data.indexOf(qe);	
		}
		
		return idx;
	}
	
	public static void main(String[] args) {
		LargestQuakes lq = new LargestQuakes();
		lq.findLargestQuakes();

	}

}
