package behaviour.modules;

import lejos.hardware.lcd.TextLCD;
import nl.hva.miw.robot.cohort13.Marvin;

public class ClearConsoleModule extends BehaviourModule {

	public ClearConsoleModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		TextLCD textLCD = marvin.getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
		textLCD.clear();
		textLCD.refresh();
		return true;
	}
}
