package behaviour.modules.general;

import behaviour.modules.BehaviourModule;
import lejos.robotics.Color;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.ColorSensorControl;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class SetBrickLedPatternModule extends BehaviourModule {
	private int pattern;
	
	/*
	  LED_BLACK                     = 0,
	  LED_GREEN                     = 1,
	  LED_RED                       = 2,
	  LED_ORANGE                    = 3,
	  LED_GREEN_FLASH               = 4,
	  LED_RED_FLASH                 = 5,
	  LED_ORANGE_FLASH              = 6,
	  LED_GREEN_PULSE               = 7,
	  LED_RED_PULSE                 = 8,
	  LED_ORANGE_PULSE              = 9,
	*/

	public SetBrickLedPatternModule(Marvin marvin, int pattern) {
		super(marvin);
		
		this.pattern = pattern;
	}
	
	@Override
	public boolean execute() {
		getMarvin().getBrick().getLED().setPattern(pattern);

		return true;
	}

}
