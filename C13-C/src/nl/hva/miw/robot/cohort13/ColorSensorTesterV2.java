package nl.hva.miw.robot.cohort13;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Calibrate;
import lejos.robotics.Color;

public class ColorSensorTesterV2 implements Calibrate{
	
	public void test(Marvin marvin) {		
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);	
		TextLCD textLCD = marvin.brick.getTextLCD();
		int testMode = 0;
		long lastTime = 0;
		int testCount = 0;
		
		//Test RGB Mode
		colorSensor.setFloodlight(false);		
	    textLCD.setAutoRefresh(false);
	    
	    textLCD.drawString("RGB mode", 2, 2);
	    Sound.beep();
	    
	    SensorMode sensorModeRGB = colorSensor.getRGBMode();
//	    SensorMode sensorModeRed = colorSensor.getRedMode();
//	    SensorMode sensorModeAmbient = colorSensor.getAmbientMode();
	    
	    float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
//	    float[] sampleRed = new float[sensorModeRed.sampleSize()];
//	    float[] sampleAmbient = new float[sensorModeAmbient.sampleSize()];
	    
	    boolean stop = false;
	    
	    while (!stop) {
	        long currentTime = System.currentTimeMillis();
	        colorSensor.setFloodlight(Color.WHITE);
    		sensorModeRGB.fetchSample(sampleRGB, 0);
	        
    		if (currentTime - lastTime > 1000) {
	        	lastTime = currentTime;
		        textLCD.refresh();
		        textLCD.clear();
		        testCount++;
		        
		        float r = sampleRGB[0];						// rood
		        float g = sampleRGB[1];						//groen
		        float b = sampleRGB[2];						//blauw
		        
		        String sR = String.format("R: %.2f", r);	//waarde output rood
		        String sG = String.format("G: %.2f", g);	//waarde output groen
		        String sB = String.format("B: %.2f ", b);
		        				    
		        textLCD.drawString(sR, 1, 2);
		        textLCD.drawString(sG, 1, 3);
		        textLCD.drawString(sB, 1, 4);  
		           	        
	        	if ((sampleRGB[0]> 0.205f) && (sampleRGB[0]< 0.215f) //bepaalde waarden, print uit kleur
		        		&& (sampleRGB[1]>0.240) && (sampleRGB[1]< 0.250f)
		        		&& (sampleRGB[2]>0.200) && (sampleRGB[2]< 0.211f)) { 
	        	        		
	        		System.out.println("Kleur is wit");
			        			       
	        	}else { 
	        	       	
	        		System.out.println("Kleur is Zwart");
	        	} 
	        }
	     }
	        
	}

	@Override
	public void startCalibration() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopCalibration() {
		// TODO Auto-generated method stub
		
	}
}
