package nl.hva.miw.robot.cohort13;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;

public class ColorSensorTester {
	
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
	    SensorMode sensorModeRed = colorSensor.getRedMode();
	    SensorMode sensorModeAmbient = colorSensor.getAmbientMode();
	    
	    float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
	    float[] sampleRed = new float[sensorModeRed.sampleSize()];
	    float[] sampleAmbient = new float[sensorModeAmbient.sampleSize()];
	    
	    boolean stop = false;
	    
	    while (!stop) {
	        long currentTime = System.currentTimeMillis();
	        
	        if (currentTime - lastTime > 1000) {
	        	lastTime = currentTime;
		        textLCD.refresh();
		        textLCD.clear();
		        testCount++;
		        
	        	if (testMode == 0) {
	        		colorSensor.setFloodlight(Color.WHITE);
	        		
			        sensorModeRGB.fetchSample(sampleRGB, 0);
			    
			        float r = sampleRGB[0];
			        float g = sampleRGB[1];
			        float b = sampleRGB[2];
			        
			        String sR = String.format("R: %.2f", r);
			        String sG = String.format("G: %.2f", g);
			        String sB = String.format("B: %.2f ", b);
			        
				    textLCD.drawString("RGB mode", 1, 1);
			        textLCD.drawString(sR, 1, 2);
			        textLCD.drawString(sG, 1, 3);
			        textLCD.drawString(sB, 1, 4);  
	        	} else if (testMode == 1) {		//Red Mode
	        		colorSensor.setFloodlight(Color.RED);
	        		colorSensor.setCurrentMode(sensorModeRed.getName());		
	        		
	        		sensorModeRed.fetchSample(sampleRed, 0);
	        		
			        float r = sampleRed[0];
			        float g = sampleRed[1];
			        float b = sampleRed[2];
			        
			        String sR = String.format("R: %.2f", r);
			        String sG = String.format("G: %.2f", g);
			        String sB = String.format("B: %.2f ", b);
			        
				    textLCD.drawString("Red mode", 2, 1);
			        textLCD.drawString(sR, 1, 2);
			        textLCD.drawString(sG, 1, 3);
			        textLCD.drawString(sB, 1, 4);  
	        	} else if (testMode == 2) {	//Ambient Mode
	        		colorSensor.setFloodlight(false);
	        		
	        		
	        		sensorModeAmbient.fetchSample(sampleAmbient, 0);
	        		
			        float r = sampleAmbient[0];
			        float g = sampleAmbient[1];
			        float b = sampleAmbient[2];
			        
			        String sR = String.format("R: %.2f", r);
			        String sG = String.format("G: %.2f", g);
			        String sB = String.format("B: %.2f ", b);
			        
				    textLCD.drawString("Ambient mode", 2, 1);
			        textLCD.drawString(sR, 1, 2);
			        textLCD.drawString(sG, 1, 3);
			        textLCD.drawString(sB, 1, 4);  
	        	}
	        }
	        
	        if (testCount > 20) {
	        	testCount = 0;
	        	testMode++;
	        	Button.LEDPattern(4);    // flash green led and
	        	Sound.beep();
	        }
	        
	        if (testMode > 2) {
	        	stop = true;
	        	colorSensor.close();
	        }
	    }  
	    
	    Button.LEDPattern(4);    // flash green led and
	    Sound.buzz();
	    textLCD.drawString("testing complete", 1, 1);
	    Sound.beep();
	    
		/*
		ColorSensor    color = new ColorSensor(SensorPort.S3);

        System.out.println("Color Demo");
        Lcd.print(2, "Press to start");
        
        Button.LEDPattern(4);    // flash green led and
        Sound.beepSequenceUp();    // make sound when ready.

        Button.waitForAnyPress();
        Button.LEDPattern(0);
        
        // run until escape button pressed.
        
        while (Button.ESCAPE.isUp())
        {
            Lcd.clear(4);
            Lcd.print(4, "ambient=%.3f", color.getAmbient());
            Delay.msDelay(250);
        }

        Delay.msDelay(1000);

        color.setRedMode();
        color.setFloodLight(Color.RED);
        color.setFloodLight(true);
        
        while (Button.ESCAPE.isUp())
        {
            Lcd.clear(5);
            Lcd.print(5, "red=%.3f", color.getRed());
            Delay.msDelay(250);
        }

        Delay.msDelay(1000);

        color.setRGBMode();
        color.setFloodLight(Color.WHITE);
        
        Color rgb;
        
        while (Button.ESCAPE.isUp())
        {
            rgb = color.getColor();
            
            Lcd.clear(6);
            Lcd.print(6, "r=%d g=%d b=%d", rgb.getRed(), rgb.getGreen(), rgb.getBlue());
            Delay.msDelay(250);
        }

        Delay.msDelay(1000);

        color.setColorIdMode();
        color.setFloodLight(false);
        
        while (Button.ESCAPE.isUp())
        {
            Lcd.clear(7);
            Lcd.print(7, "id=%s", ColorSensor.colorName(color.getColorID()));
            Delay.msDelay(250);
        }

        // free up resources.
        color.close();
        
        Sound.beepSequence();    // we are done.

        Button.LEDPattern(4);
        Button.waitForAnyPress();
		
		*/
	}
}
