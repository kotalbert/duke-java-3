package week2;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

	private Pattern p;
	
	
	public TitleLastAndMagnitudeComparator() {
		p = Pattern.compile("\\w+$");
	}
	
	@Override
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		
		// compare last words

		Matcher match1 = p.matcher(q1.getInfo());
		Matcher match2 = p.matcher(q2.getInfo());
		
		if (match1.find() && match2.find()) {
		
			String last1 = match1.group(0);
			String last2 = match2.group(0);
			
			if (last1.compareTo(last2) == 0)
				return Double.compare(q1.getMagnitude(), q2.getMagnitude());
			else return last1.compareTo(last2);
				
		}
		
		return 0;
	}

}
