package nl.hva.miw.robot.cohort13;

import java.io.File;

import lejos.hardware.Sound;

public class PlaySound extends Thread {
	private Thread thread;
	private String name;
	private int volume;
	
	public PlaySound(String name, int volume) {
		this.name = name;
		this.volume = volume;		
	}
	
	public void run() {
		Sound.playSample(new File(name), volume);
	}
	
	public void start () {
		if (thread == null) {
			thread = new Thread (this);
			thread.start ();
		}
	}
}
