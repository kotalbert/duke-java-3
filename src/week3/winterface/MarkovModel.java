package week3.winterface;

import java.util.ArrayList;

public class MarkovModel extends AbstractMarkovModel{

	public MarkovModel(int order) {
		super(order);
	}

	public String getRandomText(int numChars) {
		if (myText == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		
		int index = myRandom.nextInt(myText.length()-order);
		String key = myText.substring(index, index + order);
		sb.append(key);

		for (int k = 0; k < numChars-order; k++) {
			
			ArrayList<String> follows = getFollows(key);
			String followsRandom = follows.get(myRandom.nextInt(follows.size()));
			sb.append(followsRandom);
			key = key.substring(1) +followsRandom ;

		}

		return sb.toString();
	}
}
