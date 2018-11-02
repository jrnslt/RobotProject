package nl.hva.miw.robot.cohort13.sound;

import lejos.hardware.Sound;

public class PlayLoopedSound extends Thread {
	private Thread thread;
	private int amount;
	
	public PlayLoopedSound(int volume) {
		this.amount = amount;		
	}
	 
	public void run() { // speelt een geluid af, event. gelijktijdig met een andere handeling
		int count = 0; 
		
		while (count < amount) {
			//Sound.playSample(new File(name), volume);
			Sound.beep();
			count++;
		}
	}
	
	public void start () {
		if (thread == null) {
			thread = new Thread (this);
			thread.start ();
		}
	}
}
