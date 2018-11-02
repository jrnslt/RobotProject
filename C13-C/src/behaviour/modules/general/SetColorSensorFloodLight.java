package behaviour.modules.general;

import behaviour.modules.BehaviourModule;
import lejos.robotics.Color;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.ColorSensorControl;

public class SetColorSensorFloodLight extends BehaviourModule {
	private int color;

	public SetColorSensorFloodLight(Marvin marvin, int color) {
		super(marvin);
		
		this.color = color;
	}
	
	@Override
	public boolean execute() {
		ColorSensorControl colorSensorControlFront = getMarvin().getColorSensorControlFront();
		colorSensorControlFront.getColorSensor().setFloodlight(color);
		
		return true;
	}

}
