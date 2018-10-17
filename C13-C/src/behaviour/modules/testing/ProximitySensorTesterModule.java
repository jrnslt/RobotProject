package behaviour.modules.testing;

import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class ProximitySensorTesterModule extends TestModule {

	public ProximitySensorTesterModule(Marvin marvin, String testModuleName) {
		super(marvin, testModuleName);
	}

	@Override
	public boolean execute() {
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		EV3IRSensor afstandTester = getMarvin().PROXIMITY_SENSOR;
		final int HALF_SECOND = 500;
	
		final SampleProvider sp = afstandTester.getDistanceMode();
		
		long startTime = System.currentTimeMillis();
		long currentTime = System.currentTimeMillis();
		
		while(currentTime - startTime < 10000) {
			currentTime = System.currentTimeMillis();
			float [] sample = new float[sp.sampleSize()];
		    sp.fetchSample(sample, 0);	
		    int distanceValue = (int)sample[0];
		    
		    textLCD.refresh();
	        textLCD.clear();
	        textLCD.drawString("Distance:", 2, 1);
	        textLCD.drawString("" + distanceValue, 1, 2);
	        Delay.msDelay(HALF_SECOND);	
		}
		
		return true;
	}
}


