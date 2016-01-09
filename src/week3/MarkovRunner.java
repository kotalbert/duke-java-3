package week3;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunner {

	/**
	 * Get sample text from file as a string
	 * 
	 * @return
	 */
	private String getText() {
		FileResource fr = new FileResource("data/markov/romeo.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		return st;
	}

	public void runMarkovZero() {

		String st = getText();
		MarkovZero markov = new MarkovZero();
		markov.setRandom(1024);
		markov.setTraining(st);
		for (int k = 0; k < 3; k++) {
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	public void runMarkovOne() {
		String st = getText();
		MarkovModel markov = new MarkovModel(1);
		markov.setRandom(365);
		markov.setTraining(st);
	
		String text = markov.getRandomText(500);
		printOut(text);
	
	}

	public void runMarkovFour() {
		String st = getText();
		MarkovModel markov = new MarkovModel(4);
		markov.setRandom(715);
		markov.setTraining(st);
		for (int k = 0; k < 3; k++) {
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	public void runMarkovSix() {
		String st = getText();
		MarkovModel markov = new MarkovModel(7);
		markov.setRandom(953);
		markov.setTraining(st);
		String text = markov.getRandomText(500);
		printOut(text);

	}

	private void printOut(String s) {
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for (int k = 0; k < words.length; k++) {
			System.out.print(words[k] + " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	public static void main(String[] args) {
		MarkovRunner mr = new MarkovRunner();
//		mr.runMarkovZero();
//		 mr.runMarkovOne();
//		mr.runMarkovFour();
		mr.runMarkovSix();
	}

}
