package week1;

public class MagnitudeFilter implements Filter {

	private double min;
	private double max;
	
	public MagnitudeFilter(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		double mag = qe.getMagnitude();
		return (mag >= min && mag <= max);
	}
	
	@Override
	public String getName() {
		return "Magnitude";
	}

}
