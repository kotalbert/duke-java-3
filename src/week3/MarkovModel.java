package week3;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
	private String myText;
	private Random myRandom;
	private int order;

	public MarkovModel(int order) {
		myRandom = new Random();
		this.order = order;
	}

	public void setRandom(int seed) {
		myRandom = new Random(seed);
	}

	public void setTraining(String s) {
		myText = s.trim();
	}
	
	/**
	 * Helper function for matching key and a substring <pos, order);
	 * @param key
	 * @param pos
	 * @return
	 */
	private boolean matchKey(String key, int pos) {
		
		return key.equals(myText.substring(pos, pos + order));
		
	}
	
	/**
	 *Get the letter following key starting on pos:
	 * substring(pos+order, pos+order+1)
	 * @param pos
	 * @return
	 */
	String getFollowingLetter(int pos) {
		// index of letter following key
		int index = pos+order;
		return myText.substring(index, index+1);
	}

	public ArrayList<String> getFollows(String key) {

		ArrayList<String> follows = new ArrayList<String>();

		for (int i = 0; i < myText.length() - order; i++) {
			// if found key, add letter after the key to the list
			// key length = order
			if (matchKey(key, i))
				follows.add(getFollowingLetter(i));
		}

		return follows;

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
