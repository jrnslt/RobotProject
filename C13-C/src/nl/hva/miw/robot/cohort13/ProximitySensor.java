package nl.hva.miw.robot.cohort13;

import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

/*
 * Class for instantiating the Proximity Sensor
 */ 

public class ProximitySensor {
	
	protected EV3IRSensor proximitySensor;
	protected Marvin marvin;
	protected TextLCD textLCD; 
	
	public ProximitySensor(Marvin marvin) {
		proximitySensor = new EV3IRSensor(SensorPort.S1);
		this.marvin = marvin;
		textLCD = marvin.getBrick().getTextLCD();
	}
	
	/*
	 * Gets the distance and prints it if needed (true / false)
	 */
	
	public void getDistance(boolean showValue) {
		final SampleProvider sp = proximitySensor.getDistanceMode();
		float [] sample = new float[proximitySensor.sampleSize()];
		sp.fetchSample(sample, 0);
		int distanceValue = (int)sample[0];
		if (showValue) {
			textLCD.refresh();
	        textLCD.clear();
	        textLCD.drawString("Distance:", 2, 1);
	        textLCD.drawString("" + distanceValue, 1, 2);
	        Delay.msDelay(500);	
		}
	}
	
	
	
}
