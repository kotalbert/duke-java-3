package week3.ngram;

import java.util.Random;

public abstract class AMarkovWord implements IMarkovModel{
	protected String[] myText;
	protected Random myRandom;
	protected int order;
	
	
	public AMarkovWord(int order) {
		
		myRandom = new Random();
		this.order = order;
	}

	public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
    

    
	
	
}
