package nl.hva.miw.robot.cohort13.functionality;

import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

/**
 * Class for instantiating the Proximity Sensor
 *
 */
public class ProximityControl extends MarvinComponent {
	private EV3IRSensor proximitySensor;
	
	public ProximityControl(Marvin marvin) {
		super(marvin);
		proximitySensor = new EV3IRSensor(SensorPort.S1);
	}
	
	/**
	 * @return distance between object and proximity sensor
	 */
	public int getDistance() {
		final SampleProvider sp = proximitySensor.getDistanceMode();
		float [] sample = new float[proximitySensor.sampleSize()];
		sp.fetchSample(sample, 0);
		int distanceValue = (int)sample[0];
		
		return distanceValue;
	}
	
	/**
	 * geeft de distance terug tot de proximity sensor en print deze optioneel uit
	 * 
	 * @param showValue: if true, print distance
	 */
	public void getDistance(boolean showValue) {
		int distanceValue = getDistance();
		
		if (showValue) {
			TextLCD textLCD = getMarvin().getBrick().getTextLCD();
			textLCD.refresh();
	        textLCD.clear();
	        textLCD.drawString("Distance:", 2, 1);
	        textLCD.drawString("" + distanceValue, 1, 2);
	        Delay.msDelay(500);	
		}
	}
	
	public EV3IRSensor getPrximitySensor() {
		return proximitySensor;
	}
}
