package week3.winterface;

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel {
	
	private HashMap<String, ArrayList<String>> map;
	
	public EfficientMarkovModel(int order) {
		super(order);
		map = new HashMap<String, ArrayList<String>>();
	}
	
	/**
	 * Check if key is in map, 
	 * Add a ArrayList with key if key not in map.
	 * @param key
	 */
	private void buildMap(String key) {
		if (!map.containsKey(key)) {
			ArrayList<String> list = getFollows(key);
			if (list.isEmpty()) list.add(" ");
			map.put(key, list);
		}
		
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
			buildMap(key);
			ArrayList<String> follows = map.get(key);
			String followsRandom = follows.get(myRandom.nextInt(follows.size()));
			sb.append(followsRandom);
			key = key.substring(1) +followsRandom ;

		}

		return sb.toString();
	}
	
	public void printHashMapInfo() {
		System.out.printf("Map size:\t%d\nMax size:\t%d\n", 
				mapSize(), 
				longestFollowsSize());
		for (String key : map.keySet()) {
			System.out.printf("Key:\t[%s]\tvalues: ", key);
			System.out.println(map.get(key));
		}
		
	}
	
	public int mapSize() {
		return map.size();
	}
	
	public int longestFollowsSize() {
		int maxSize = 0;
		for (String key : map.keySet()) {
			maxSize = Math.max(maxSize, map.get(key).size());
		}
		
		return maxSize;
	}
	
	@Override
	public String toString() {
		return "Efficient Markov Model order " + order;
	}
}
