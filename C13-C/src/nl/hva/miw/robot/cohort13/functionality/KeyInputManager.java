package nl.hva.miw.robot.cohort13.functionality;

import lejos.hardware.Key;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class KeyInputManager {
	private Marvin marvin;
	
	public KeyInputManager(Marvin marvin) {
		this.marvin = marvin;
	}
	
	public void waitForKey(Key key) {
		while(key.isUp()) {
			Delay.msDelay(100);
		}
		while(key.isDown()) {
			Delay.msDelay(100);
		}
	}

}
