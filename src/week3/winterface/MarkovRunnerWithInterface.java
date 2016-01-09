package week3.winterface;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;
import week3.MarkovZero; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
        	markov.setRandom(seed);
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 10;
//        MarkovZero mz = new MarkovZero();
//        runModel(mz, st, size, seed);
//    
//        MarkovOne mOne = new MarkovOne();
//        runModel(mOne, st, size, seed);
//        
//        MarkovModel mThree = new MarkovModel(3);
//        runModel(mThree, st, size, seed);
//        
//        MarkovModel mFour = new MarkovModel(4);
//        runModel(mFour, st, size, seed);
		
		MarkovModel mFour = new MarkovModel(4);
		EfficientMarkovModel mFourEfficient = new EfficientMarkovModel(4);
		
		runModel(mFour, st, size, seed);
		runModel(mFourEfficient, st, size, seed);
		

    }

    
	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
	
	public static void main(String[] args) {
		MarkovRunnerWithInterface mr = new MarkovRunnerWithInterface();
		mr.runMarkov();
	}
}
