package behaviour.modules.testing;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class ColorSensorTesterModule extends TestModule {
	
	public ColorSensorTesterModule(Marvin marvin, String testModuleName) {
		super(marvin, testModuleName);
	}

	@Override
	public boolean execute() {


		EV3ColorSensor colorSensor = marvin.colorSensorA;
		

		TextLCD textLCD = marvin.getBrick().getTextLCD();
		int testMode = 1;
		int testCount = 0;
			
	    textLCD.setAutoRefresh(false);
	    
	    SensorMode sensorModeRGB = colorSensor.getRGBMode();
	    SensorMode sensorModeRed = colorSensor.getRedMode();
	    SensorMode sensorModeAmbient = colorSensor.getAmbientMode();
	    
	    float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
	    float[] sampleRed = new float[sensorModeRed.sampleSize()];
	    float[] sampleAmbient = new float[sensorModeAmbient.sampleSize()];
	  
	    while (testMode == 1) {
	    	
//	    	(true) {
//	        textLCD.setAutoRefresh(false);
//	        textLCD.refresh();
//	        textLCD.clear();
//	        
//        	if (testMode == 0) {
//        		colorSensor.setFloodlight(Color.WHITE);
//        		
//		        sensorModeRGB.fetchSample(sampleRGB, 0);
//		    
//		        float r = sampleRGB[0];
//		        float g = sampleRGB[1];
//		        float b = sampleRGB[2];
//		        
//		        String sR = String.format("R: %.2f", r);
//		        String sG = String.format("G: %.2f", g);
//		        String sB = String.format("B: %.2f ", b);
//		        
//			    textLCD.drawString("RGB mode", 1, 1);
//		        textLCD.drawString(sR, 1, 2);
//		        textLCD.drawString(sG, 1, 3);
//		        textLCD.drawString(sB, 1, 4);  
//        	} 
//	    	else if (testMode == 1) {	
	    		//Red Mode
        		colorSensor.setFloodlight(Color.RED);
        		colorSensor.setCurrentMode(sensorModeRed.getName());		
        		
        		sensorModeRed.fetchSample(sampleRed, 0);
        		
		        float r = sampleRed[0];
		        
		        String sR = String.format("R: %.2f", r);
		        
			    textLCD.drawString("Red mode", 2, 1);
		        textLCD.drawString(sR, 1, 2);
//        	} else if (testMode == 2) {	//Ambient Mode
//        		colorSensor.setFloodlight(false);
//        		colorSensor.setCurrentMode(sensorModeAmbient.getName());
//        		
//        		sensorModeAmbient.fetchSample(sampleAmbient, 0);
//        		
//		        float r = sampleAmbient[0];
//		        //float g = sampleAmbient[1];
//		        //float b = sampleAmbient[2];
//		        
//		        String sR = String.format("R: %.2f", r);
//		        
//		        //String sG = String.format("G: %.2f", g);
//		        //String sB = String.format("B: %.2f ", b);
//		        
//			    textLCD.drawString("Ambient mode", 2, 1);
//		        textLCD.drawString(sR, 1, 2);
//		        textLCD.drawString("" + sampleAmbient.length, 1, 3);
//		        //textLCD.drawString(sG, 1, 3);
//		        //textLCD.drawString(sB, 1, 4);  
        	}
        	
        	//testCount++;
        	
	     //   Delay.msDelay(500);
	        
//	        if (testCount > 20) {
//	        	testCount = 0;
//	        	testMode++;
//	        	Button.LEDPattern(4);    // flash green led and
//	        	Sound.beep();
//	        }
//	        
//	        if (testMode > 1) {
//	        	break;
//	        }
//	    }  

	    return true;
	}

}
