package nl.hva.miw.robot.cohort13.functionality;

import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.resources.Colors;

/*
* Initialiseert de kleurensensor en calibreert deze.
* De sensor gebruikt de RGB modus (white)
*/
public class ColorSensorControl extends MarvinComponent {
    private EV3ColorSensor colorSensor;
    private float red =1;
    private float green =1;
    private float blue =1;
    boolean colorSet = false;
        
    public ColorSensorControl(Marvin marvin, Port sensorPort) {
        super(marvin);
        colorSensor = new EV3ColorSensor(sensorPort);   
    }
    
    public EV3ColorSensor getColorSensor() {
        return colorSensor;
    }
    
    public void calibrateSensor() {   
        TextLCD textLCD = getMarvin().getBrick().getTextLCD();
        SensorMode sensorModeRGB = colorSensor.getRGBMode(); 
        float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
        colorSensor.setFloodlight(Color.WHITE);
        colorSensor.setCurrentMode(sensorModeRGB.getName());
        
        Delay.msDelay(2000);
        
        while (colorSet == false) {
        	sensorModeRGB.fetchSample(sampleRGB, 0);
        	
            textLCD.setAutoRefresh(false);
            textLCD.refresh();
            textLCD.clear();
               
            textLCD.drawString("Calibrate white: ", 1, 1);
            textLCD.drawString("R: " + sampleRGB[0], 1, 3);
            textLCD.drawString("G: " + sampleRGB[1], 1, 4);
            textLCD.drawString("B: " + sampleRGB[2], 1, 5);
        	
			if (Button.ENTER.isDown()) {
	            red = sampleRGB[0];
	            green = sampleRGB[1];
	            blue = sampleRGB[2];
	            colorSet = true;
			}
        }
    }
    
    public float getRed(float r) {
        return r/this.red;
    }
    public float getBlue(float b) {
        return b/this.blue;
    }
    public float getGreen(float g) {
        return g/this.green;
    }
    
    public void ColorTesterTest() {
        TextLCD textLCD = getMarvin().getBrick().getTextLCD();
        long startTime = System.currentTimeMillis();
        long lastTime = System.currentTimeMillis();
        while (lastTime - startTime < 90000) {
            SensorMode sensorModeRGB = colorSensor.getRGBMode(); 
            float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
            sensorModeRGB.fetchSample(sampleRGB, 0);
            
            float r = sampleRGB[0];
            float g = sampleRGB[1];
            float b = sampleRGB[2];
            
            
            lastTime = System.currentTimeMillis();
            float redC = getRed(r);
            float greenC = getGreen(g);
            float blueC = getBlue(b);
            textLCD.refresh();
            textLCD.clear();
                    
            textLCD.drawString("R:" +  redC, 1, 3);
            textLCD.drawString("G:" +  greenC, 1, 4);
            textLCD.drawString("B:" +  blueC, 1, 5);
            
            ArrayList<MColor> colors = new ArrayList<>();
            colors.add(Colors.RED);
            colors.add(Colors.GREEN);
            colors.add(Colors.BLUE);
            colors.add(Colors.ORANGE);
            colors.add(Colors.WHITE);
            colors.add(Colors.BLACK);
            
            MColor closestColor = getMarvin().getClosestColorFinder().
            		getClosestColor(colors, new MColor("", redC, greenC, blueC));
            textLCD.drawString("" +  closestColor.getColorName(), 1, 6);
        }
    }
}