package week3.winterface;

import java.text.DecimalFormat;

import edu.duke.FileResource;

public class TestEfficientMarkov {
	
	
    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
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
	
	public void testFollowsSize(){
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		
		EfficientMarkovModel em = new EfficientMarkovModel(5);
		em.setRandom(615);
		runModel(em, st, 200);
		System.out.println("HashMap size:\t" + em.mapSize());
		System.out.println("Longest follows:\t" + em.longestFollowsSize());
		
	}
	
	public void testHashMap() {
		EfficientMarkovModel em = new EfficientMarkovModel(2);
		em.setRandom(42);
		String st = "yes-this-is-a-thin-pretty-pink-thistle";
		runModel(em, st, 50);
		em.printHashMapInfo();
	}
	
	public void testInfo() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		
		EfficientMarkovModel em = new EfficientMarkovModel(5);
		em.setRandom(615);
		runModel(em, st, 200);
		em.printHashMapInfo();
		
		
	}
	
	public void runMarkovOne() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		
		EfficientMarkovModel markov = new EfficientMarkovModel(1);
		markov.setRandom(42);
		runModel(markov, st, 200);
	}
	
	public void runMarkovFour() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		
		EfficientMarkovModel markov = new EfficientMarkovModel(2);
		markov.setRandom(25);
		runModel(markov, st, 200);
	}
	
	public void runMarkovSix() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		
		EfficientMarkovModel markov = new EfficientMarkovModel(5);
		markov.setRandom(531);
		runModel(markov, st, 200);
	}
	
	public void compareModels() {
		System.out.println("Running model comparison...");
		
		int order = 6;
		int seed = 120;
		int size = 1000;
		int times = 3;
		
		MarkovModel basic = new MarkovModel(order);
		EfficientMarkovModel efficient = new EfficientMarkovModel(order);
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		
		basic.setRandom(seed);
		
		efficient.setRandom(seed);
		
		
		// Test basic model
		long basicTime = System.nanoTime();
		basic.setTraining(st);
		runModelTimes(basic, times, size);
		basicTime = System.nanoTime() - basicTime;
		
		// Test efficient model
		long effTime = System.nanoTime();
		efficient.setTraining(st);
		runModelTimes(efficient, times, size);
		effTime = System.nanoTime() - effTime;
		
		DecimalFormat timeFormat = new DecimalFormat("#.##########");
		
		
		System.out.printf("Basic model, time:\t%s\n"
				, timeFormat.format(basicTime/ 1000000000.0));
		System.out.printf("Efficient model, time:\t%s\n"
				, timeFormat.format(effTime/ 1000000000.0));
		
	}
	
	private void runModelTimes(IMarkovModel markov, int times, int size) {
		
		for (int i=0; i<times; i++)
			markov.getRandomText(size);
	}
	
	public static void main(String[] args) {
		TestEfficientMarkov t = new TestEfficientMarkov();
//		t.compareModels();
//		t.testHashMap();
		t.runMarkovSix();

	}

}
