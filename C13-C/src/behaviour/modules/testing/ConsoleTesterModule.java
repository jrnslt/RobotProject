package behaviour.modules.testing;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Button;
import lejos.hardware.lcd.TextLCD;
import nl.hva.miw.robot.cohort13.Marvin;

public class ConsoleTesterModule extends BehaviourModule {

	public ConsoleTesterModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		
	    textLCD.refresh();
        textLCD.clear();
        textLCD.drawString("Press Enter key to continue:", 0, 1);
		getMarvin().waitForKey(Button.ENTER);
		return true;
	}
}
