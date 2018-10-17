package behaviour.modules.testing;

import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class TouchSensorTesterModule extends TestModule{
	
	public TouchSensorTesterModule(Marvin marvin, String testModuleName) {
		super(marvin, testModuleName);
	}

	@Override
	public boolean execute() {  
		EV3TouchSensor touchSensor = getMarvin().TOUCH_SENSOR;
	 
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		 
		SensorMode sensorMode = touchSensor.getTouchMode();
		float[] sample = new float[sensorMode.sampleSize()];
	
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		
		while(lastTime - startTime < 10000) {	        
	        lastTime = System.currentTimeMillis();
	        
	        touchSensor.fetchSample(sample, 0);
			
			for (int i= 0; i < sample.length;i++)	{
				textLCD.setAutoRefresh(false);
			    textLCD.refresh();
		        textLCD.clear();
		        textLCD.drawString("Touch mode", 1, 1);
		        textLCD.drawString("" + sample[i], 1, 2);
			}
	        
	        Delay.msDelay(500);
		}
		
		return true;
	}
}