package behaviour.modules.testing;

import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import nl.hva.miw.robot.cohort13.Marvin;

public class TouchSensorTesterModule extends TestModule{
	
	public TouchSensorTesterModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public void execute() {
		
		int testMode = 0;
		long lastTime = 0;
	   
		EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S3);
	 
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
		textLCD.drawString("Touch mode", 1, 2);
	
		Sound.beep();
		 
		SensorMode sensorMode = touch.getTouchMode();
		float[] sample = new float[sensorMode.sampleSize()];
	
	
		while(testMode == 0) {	        
	        long currentTime = System.currentTimeMillis();
	        
	        if (currentTime - lastTime > 1000) {
				touch.fetchSample(sample, 0);
			    textLCD.refresh();
		        textLCD.clear();
				lastTime = currentTime;
				
				for (int i= 0; i < sample.length;i++)	{
					System.out.println(sample[i]);
				}
	        }
		}
	}
}