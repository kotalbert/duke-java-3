package week1;

import java.util.regex.Pattern;

public class PhraseFilter implements Filter {

	private Pattern p;

	public PhraseFilter(String where, String phrase) {

		switch (where) {

		// match start of string
		case "start":
			p = Pattern.compile("^" + phrase);
			break;

		// match end of string
		case "end":
			p = Pattern.compile(phrase + "$");
			break;

		// match any
		default:
			p = Pattern.compile(phrase);
			break;
		}

	}

	@Override
	public boolean satisfies(QuakeEntry qe) {
		return p.matcher(qe.getInfo()).find();
	}

	@Override
	public String getName() {
		return "Phrase";
	}

}
