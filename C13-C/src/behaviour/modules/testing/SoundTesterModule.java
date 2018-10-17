package behaviour.modules.testing;

import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class SoundTesterModule extends TestModule {
	
	public SoundTesterModule(Marvin marvin, String testModuleName) {
		super(marvin, testModuleName);
	}

	@Override
	public boolean execute() {
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		float x = 0;
		
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		
		while (lastTime - startTime < 10000) {
			int frequency = (int)(Math.sin(x) * 100);
			Sound.playTone(frequency, 1, 50);
			x += 0.1f;
		}
		
		Sound.systemSound(false, 1);
		
		Delay.msDelay(1000);
		
		Sound.systemSound(false, 2);
		
		Delay.msDelay(1000);
		
		Sound.systemSound(false, 3);
		
		Delay.msDelay(1000);
		
		Sound.systemSound(false, 4);
	    
	    return true;
	}
}
