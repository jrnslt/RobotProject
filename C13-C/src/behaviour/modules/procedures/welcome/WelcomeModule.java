package behaviour.modules.procedures.welcome;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Brick;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

/**
 * @author daniel
 *
 * Geef welkoms groet
 *
 */
public class WelcomeModule extends BehaviourModule {

	public WelcomeModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		Brick brick = getMarvin().getBrick();
		TextLCD display = brick.getTextLCD();
		display.drawString("Welkom Ingrid", 0, 3);
		display.drawString("Team Charlie!", 0, 4);		
		display.drawString(brick.getName(), 0, 7);	
		
		Delay.msDelay(1000);
		
		return true;
	}
}
