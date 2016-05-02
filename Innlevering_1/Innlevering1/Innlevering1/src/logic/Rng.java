package logic;

import java.util.Random;

public class Rng {
	public int waitTime(){
		Random rand = new Random();
		int tmp = rand.nextInt(10000);
		return tmp;
	}
	
	public int sleepTime(){
		Random rand = new Random();
		int tmp = rand.nextInt(3000);
		return tmp;
	}
	
}
