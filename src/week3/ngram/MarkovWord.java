package week3.ngram;

import java.util.ArrayList;

public class MarkovWord extends AMarkovWord {

	public MarkovWord(int order) {
		super(order);
	}
	
    private int indexOf(String[] words, WordGram target, int start) {
    	
    	for (int i=start;i<words.length-order;i++) {
    		WordGram wg = new WordGram(words,i,order);
    		if (wg.equals(target)) return i;
    	}
    	
    	return -1;
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
    	ArrayList<String> follows = new ArrayList<String>();
    	
    	int index = indexOf(myText, kGram, 0);
    	while (index!=-1) {
    		follows.add(myText[index+order]);
    		index = indexOf(myText, kGram, index+1);
    	}
    	
    	return follows;
    }
	
	@Override
	public String getRandomText(int numChars) {
		
		StringBuilder sb = new StringBuilder();
		
		int index = myRandom.nextInt(myText.length-order);
		WordGram key = new WordGram(myText,index,order);

		sb.append(key.toString());
		
		for (int i=0;i<numChars-order;i++) {
			ArrayList<String> follows = getFollows(key);
			if (follows == null || follows.size()==0) break;
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			append(sb, next);
			key = key.shiftAdd(next);
		}
		
		return sb.toString().trim();
	}

}
