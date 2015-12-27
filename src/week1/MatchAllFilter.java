package week1;

import java.util.ArrayList;

public class MatchAllFilter implements Filter {

	private ArrayList<Filter> filters;
	
	public MatchAllFilter() {
		filters = new ArrayList<Filter>();
	}
	
	public void addFilter(Filter f) {
		filters.add(f);
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		
		for (Filter f : filters) {
			if (!f.satisfies(qe)) return false;
		}
		return true;
	}

	@Override
	public String getName() {
		StringBuilder sb = new StringBuilder("Filters used: ");
		
		for (Filter f : filters) {
			sb.append(f.getName()+ " ");
		}
		
		return sb.toString();
	}

}
