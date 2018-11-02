package nl.hva.miw.robot.cohort13.functionality;

import lejos.hardware.Key;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

/*
 *
 * Deze klasse regelt de knoppen. Wacht op input van gebruiker
 *
 */
public class KeyInputControl extends MarvinComponent {

	public KeyInputControl(Marvin marvin) {
		super(marvin);
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
