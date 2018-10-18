package behaviour.modules.testing;

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
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		float x = 0;
		
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		
		while (lastTime - startTime < 10000) {
			lastTime = System.currentTimeMillis(); 
			int frequency = (int)(Math.sin(x) * 100);
			Sound.playTone(frequency, 1, 50);
			x += 0.1f;
		}

		
		Sound.beep();
		Delay.msDelay(1000);
		
		Sound.beepSequence();
		Delay.msDelay(1000);
		
		Sound.beepSequenceUp();
		Delay.msDelay(1000);
		
		Sound.buzz();
		Delay.msDelay(1000);
		
		Sound.systemSound(false, 1);
		Delay.msDelay(1000);
		
		Sound.systemSound(false, 2);
		Delay.msDelay(1000);
		
		Sound.systemSound(false, 3);
		Delay.msDelay(1000);
		
		Sound.systemSound(false, 4);
		Delay.msDelay(1000);
		
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
