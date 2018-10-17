package behaviour.modules;

import lejos.hardware.Brick;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class WelcomeModule extends BehaviourModule {

	public WelcomeModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		Brick brick = getMarvin().getBrick();
		TextLCD display = brick.getTextLCD();
		display.drawString("Welkom", 0, 3);
		display.drawString("Team Charlie!", 0, 4);		
		display.drawString(brick.getName(), 0, 7);	
		
		Delay.msDelay(1000);
		
		return true;
	}
}
