package week2;

public class QuakeEntry implements Comparable<QuakeEntry>{
	
	private Location myLocation;
	private String title;
	private double depth;
	private double magnitude;

	public QuakeEntry(double lat, double lon, double mag, 
	                  String t, double d) {
		myLocation = new Location(lat,lon);
		
		magnitude = mag;
		title = t;
		depth = d;
	}
	
	public Location getLocation(){
		return myLocation;
	}
	
	public double getMagnitude(){
		return magnitude;
	}
	
	public String getInfo(){
		return title;
	}
	
	public double getDepth(){
		return depth;
	}

	// Assignment 1: compareTo Method
	@Override
	public int compareTo(QuakeEntry loc) {
		
		// if magnitude tie, compare by depth
		if (Double.compare(magnitude, loc.getMagnitude()) == 0) {
			
			return Double.compare(depth, loc.getDepth());
		}

//		double difflat = myLocation.getLatitude() - loc.myLocation.getLatitude();
//		if (Math.abs(difflat) < 0.001) {
//			double diff = myLocation.getLongitude() - loc.myLocation.getLongitude();
//			if (diff < 0) return -1;
//			if (diff > 0) return 1;
//			return 0;
//		}
//		if (difflat < 0) return -1;
//		if (difflat > 0) return 1;
		
		// compare by magnitude
		return Double.compare(magnitude, loc.getMagnitude());
	}
	
	public String toString(){
		return String.format("(%3.2f, %3.2f), mag = %3.2f, depth = %3.2f, title = %s", myLocation.getLatitude(),myLocation.getLongitude(),magnitude,depth,title);
	}
	
}