package week1;

public class DistanceFilter implements Filter {

	Location loc;
	double maxDist;
	
	public DistanceFilter(Location loc, double maxDist) {
		this.loc = loc;
		this.maxDist = maxDist;
		
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		double dist = loc.distanceTo(qe.getLocation());
		return (dist<maxDist);
	}

	@Override
	public String getName() {
		return "Distance";
	}

}
