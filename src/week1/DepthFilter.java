package week1;

public class DepthFilter implements Filter {

	private double min;
	private double max;
	
	public DepthFilter(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		double depth = qe.getDepth();
		return (depth >= min && depth <= max);
	}

	@Override
	public String getName() {
		return "Depth";
	}

}
