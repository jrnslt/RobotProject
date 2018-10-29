package behaviour.modules;

import lejos.hardware.Button;
import lejos.hardware.lcd.TextLCD;
import nl.hva.miw.robot.cohort13.Marvin;

public class WaitForEnterKeyModule extends BehaviourModule {
	
	public WaitForEnterKeyModule(Marvin marvin) {
		super(marvin);
	}

	/**
	 * @return true after key is pressed
	 */
	@Override
	public boolean execute() {
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		
	    textLCD.refresh();
        textLCD.clear();
        textLCD.drawString("Press Enter key to continue:", 0, 1);
		getMarvin().getKeyInputManager().waitForKey(Button.ENTER);
		return true;
	}
}
