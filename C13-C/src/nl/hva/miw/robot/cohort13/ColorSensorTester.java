package nl.hva.miw.robot.cohort13;

import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
//import ev3.exercises.library.ColorSensor;

public class ColorSensorTester {
	
	
	
	
	public void test(Marvin marvin) {
	    /*
	    Verschillende modes:
	    	- RGB mode
	    	- Red Mode
	    	- Ambient Mode	
	    */
		
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);	
		TextLCD textLCD = marvin.brick.getTextLCD();
		int testMode = 0;
		long lastTime = 0;
		
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
	    
	    while(testMode == 0) {	        
	        long currentTime = System.currentTimeMillis();
	        
	        if (currentTime - lastTime > 1000) {
		        sensorModeRGB.fetchSample(sampleRGB, 0);
		        textLCD.refresh();
		        textLCD.clear();
	        	
	        	lastTime = currentTime;
	        
		        float r = sampleRGB[0];
		        float g = sampleRGB[1];
		        float b = sampleRGB[2];
		        
		        String sR = String.format("R: %.2f", r);
		        String sG = String.format("G: %.2f", g);
		        String sB = String.format("B: %.2f ", b);
		        
		        textLCD.drawString(sR, 1, 1);
		        textLCD.drawString(sG, 1, 2);
		        textLCD.drawString(sB, 1, 3);
		        /*
		        System.out.println("R: " + r + " G: " + g + " B: " + b);
		        textLCD.drawString("R: " + sample[0], 1, 1);
		        textLCD.drawString("G: " + sample[1], 1, 2);
		        textLCD.drawString("B: " + sample[2], 1, 3);
		        */
	        }
	    }
		
        	
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
