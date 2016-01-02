package week3.winterface;
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

public class MarkovZero extends AbstractMarkovModel {

	
	public MarkovZero() {
		super(0);
	}
	
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int k=0; k < numChars; k++){
			int index = myRandom.nextInt(myText.length());
			sb.append(myText.charAt(index));
		}
		
		return sb.toString();
	}
}
