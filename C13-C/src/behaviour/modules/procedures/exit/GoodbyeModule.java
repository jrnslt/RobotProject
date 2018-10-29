package behaviour.modules.procedures.exit;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Brick;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class GoodbyeModule extends BehaviourModule {

	public GoodbyeModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		Brick brick = getMarvin().getBrick();
		TextLCD display = brick.getTextLCD();
		display.drawString("Goodbye", 0, 3);
		display.drawString(brick.getName(), 0, 7);	
		
		Delay.msDelay(1000);
		
		return true;
	}
}
