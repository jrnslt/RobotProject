package behaviour.modules.procedures.testing;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

/**
 * 
 *
 * Print bericht dat testen begint voor een bepaalde module
 *
 */
public class StartTestMessageModule extends BehaviourModule {
	private String testModuleName;
	
	public StartTestMessageModule(Marvin marvin, String testModuleName) {
		super(marvin);
		this.testModuleName = testModuleName;
	}

	@Override
	public boolean execute() {
	    Button.LEDPattern(2);    // flash green led
	    TextLCD textLCD = getMarvin().getBrick().getTextLCD();
	    
	    Sound.buzz();
        textLCD.refresh();
        textLCD.clear();
	    textLCD.drawString("test: " + testModuleName, 1, 1);
	    textLCD.drawString("starting...", 1, 2);
	    Sound.beep();
	    Delay.msDelay(500);
	    
		return true;
	}
}
