package nl.hva.miw.robot.cohort13;

import java.io.File;

import lejos.hardware.Sound;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.resources.Sounds;

public class PlayWoopWoop extends Thread {
	private Thread thread;
	private int volume;
	
	public PlayWoopWoop(int volume) {
		this.volume = volume;		
	}
	public void run() { // speelt een geluid af, event. gelijktijdig met een andere handeling
			//Sound.playSample(new File(name), volume);
		File testbestand = new File(Sounds.woopwoop); //bestand met deze naam is geupload in het EV3 programma
		Sound.playSample(testbestand, Sound.VOL_MAX); //Speelt een bestand af op max volume
		Delay.msDelay(1000);
	}
	
	public void start () {
		if (thread == null) {
			thread = new Thread (this);
			thread.start ();
		}
	}
}
