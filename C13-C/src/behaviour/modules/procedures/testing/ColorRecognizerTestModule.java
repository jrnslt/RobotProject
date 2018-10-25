package behaviour.modules.procedures.testing;

import java.util.ArrayList;

import behaviour.modules.BehaviourModule;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.MColor;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class ColorRecognizerTestModule extends BehaviourModule {

	private EV3ColorSensor colorSensor;
	
	public ColorRecognizerTestModule(Marvin marvin, EV3ColorSensor colorSensor, String testModuleName) {
		super(marvin);
		this.colorSensor = colorSensor;
	}

	@Override
	public boolean execute() {
		TextLCD textLCD = marvin.getBrick().getTextLCD();
		int testCount = 0;
			
	    textLCD.setAutoRefresh(false);
	    
	    SensorMode sensorModeRGB = colorSensor.getRGBMode();   
	    float[] sampleRGB = new float[sensorModeRGB.sampleSize()];

		colorSensor.setFloodlight(Color.WHITE);
		colorSensor.setCurrentMode(sensorModeRGB.getName());		
	    
		ArrayList<MColor> colors = new ArrayList<>();
		colors.add(Colors.BLUE);
		colors.add(Colors.ORANGE);
		colors.add(Colors.RED);
		colors.add(Colors.GREEN);
		
	    while (true) {
	        textLCD.setAutoRefresh(false);
	        textLCD.refresh();
	        textLCD.clear();
        	
        	sensorModeRGB.fetchSample(sampleRGB, 0);
	    
	        float r = sampleRGB[0];
	        float g = sampleRGB[1];
	        float b = sampleRGB[2];
	       
	        MColor color = new MColor("", r, g, b).normalize();
	        MColor closestColorAdjusted = getMarvin().getClosestColorFinder().getClosestColor(
	        		colors, 
	        		color.getRed(), 
	        		color.getGreen(), 
	        		color.getBlue());
	        
		    textLCD.drawString("Cube Color: " + testCount, 1, 1);
		    textLCD.drawString("" + closestColorAdjusted.getColorName(), 1, 2);
		    textLCD.drawString(""  + ", " +  color.getRed(), 1, 3);
		    textLCD.drawString(""  + ", " +  color.getGreen(), 1, 4);
		    textLCD.drawString(""  + ", " +  color.getBlue(), 1, 5);
  
        	testCount++;
        	
        	Delay.msDelay(250);
	        
	        if (testCount > 150) {
	        	break;
	        }
	    }  

	    return true;
	}

}
