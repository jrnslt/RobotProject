package behaviour.modules.procedures.testing;

import java.io.File;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

/**
 * @author daniel
 * 
 * Test procedure module voor het testen van geluid
 *
 */
public class SoundTesterModule extends BehaviourModule {
	
	public SoundTesterModule(Marvin marvin, String testModuleName) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		//Sound.playNote(inst, freq, len);
		//Sound.playSample(file);
		//Sound.playSample(file, vol)
		//Sound.playSample(data, offset, len, freq, vol)
		//Sound.playTone(freq, duration);
		//Sound.playTone(aFrequency, aDuration, aVolume);\
		//Sound.twoBeeps();
		//Sound.ASCENDING
		//Sound.BEEP
		//Sound.DESCENDING
		//Sound.DOUBLE_BEEP
		//Sound.FLUTE
		//Sound.PIANO
		//Sound.VOL_MAX
		//Sound.XYLOPHONE
	    return true;
	}
}
