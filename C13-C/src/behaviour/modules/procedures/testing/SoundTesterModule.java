package behaviour.modules.procedures.testing;

import java.io.File;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

/**
 * 
 * Test procedure module voor het testen van geluid
 *
 */
public class SoundTesterModule extends BehaviourModule {
	
	public SoundTesterModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		Sound.twoBeeps();
	    return true;
	}
}
