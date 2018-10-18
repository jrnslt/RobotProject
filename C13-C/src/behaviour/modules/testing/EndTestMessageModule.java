package behaviour.modules.testing;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

/**
 * @author daniel
 *
 * Geeft een bericht dat testen complete is
 * 
 */
public class EndTestMessageModule extends BehaviourModule {
	private String testModuleName;
	
	public EndTestMessageModule(Marvin marvin, String testModuleName) {
		super(marvin);
		this.testModuleName = testModuleName;
	}

	@Override
	public boolean execute() {
	    Button.LEDPattern(4);    // flash green led
	    TextLCD textLCD = getMarvin().getBrick().getTextLCD();
	    
	    Sound.buzz();
        textLCD.refresh();
        textLCD.clear();
	    textLCD.drawString("test: " + testModuleName, 1, 1);
	    textLCD.drawString("complete...", 1, 2);
	    Sound.beep();
	    Delay.msDelay(500);
		return true;
	}

}
