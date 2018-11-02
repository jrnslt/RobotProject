package behaviour.modules.general;

import behaviour.modules.BehaviourModule;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.ColorSensorControl;

public class SetColorModeRGB extends BehaviourModule {

	public SetColorModeRGB(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		ColorSensorControl colorSensorControl = getMarvin().getColorSensorControlDown();
		EV3ColorSensor colorSensor = colorSensorControl.getColorSensor();
		
		SensorMode sensorModeRGB = colorSensor.getRGBMode();
		colorSensor.setFloodlight(Color.WHITE);
		colorSensor.setCurrentMode(sensorModeRGB.getName());
		
		return true;
	}

}
