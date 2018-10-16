package nl.hva.miw.robot.cohort13;

import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;

public class SoundTester {
	
	public void test(Marvin marvin) {
		TextLCD textLCD = marvin.brick.getTextLCD();
		long startTime = System.currentTimeMillis();
		float x = 0;
		
		while (System.currentTimeMillis() - startTime < 15000) {
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
		
	    Sound.buzz();
        textLCD.refresh();
        textLCD.clear();
	    textLCD.drawString("testing complete", 1, 1);
	    Sound.beep();
	}
}
