package nl.hva.miw.robot.cohort13.functionality;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import nl.hva.miw.robot.cohort13.Marvin;

public class ColorSensorControl extends MarvinComponent {
	private EV3ColorSensor colorSensor;
	
	public ColorSensorControl(Marvin marvin, Port sensorPort) {
		super(marvin);
		colorSensor = new EV3ColorSensor(sensorPort);	
	}
	
	public EV3ColorSensor getColorSensor() {
		return colorSensor;
	}
}
