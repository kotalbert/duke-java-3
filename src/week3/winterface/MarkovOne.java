package week3.winterface;

import java.util.ArrayList;

public class MarkovOne extends AbstractMarkovModel {

	public MarkovOne() {
		super(1);
	}

	public String getRandomText(int numChars) {
		if (myText == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - 1);
		String key = myText.substring(index, index + 1);
		sb.append(key);

		for (int k = 0; k < numChars - 1; k++) {
			ArrayList<String> follows = getFollows(key);
			String followsRandom = follows.get(myRandom.nextInt(follows.size()));
			sb.append(followsRandom);
			key = followsRandom;
		}

		return sb.toString();
	}
}
