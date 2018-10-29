package behaviour.modules.procedures.testing;

import java.util.ArrayList;

import behaviour.modules.BehaviourModule;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.ClosestColorFinder;
import nl.hva.miw.robot.cohort13.functionality.ColorSensorControl;
import nl.hva.miw.robot.cohort13.functionality.CubeFinder;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class CubeRecognizerTestModule extends BehaviourModule {
	private ColorSensorControl colorSensorControl;
	
	public CubeRecognizerTestModule(Marvin marvin, ColorSensorControl colorSensorControl, String testModuleName) {
		super(marvin);
		this.colorSensorControl = colorSensorControl;
	}

	@Override
	public boolean execute() {
		EV3ColorSensor colorSensor = colorSensorControl.getColorSensor();
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		int testCount = 0;
			
	    textLCD.setAutoRefresh(false);
	    
	    SensorMode sensorModeRGB = colorSensor.getRGBMode();   
		colorSensor.setFloodlight(Color.WHITE);
		colorSensor.setCurrentMode(sensorModeRGB.getName());		
		
		float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
		
		ClosestColorFinder closestColorFinder = getMarvin().getClosestColorFinder();
		ArrayList<MColor> colors = new ArrayList<>();
		colors.add(Colors.BLUE);
		colors.add(Colors.ORANGE);
		colors.add(Colors.RED);
		colors.add(Colors.GREEN);
		
	    while (true) {
	        textLCD.setAutoRefresh(false);
	        textLCD.refresh();
	        textLCD.clear();
	        
		    int distanceValue = getMarvin().getProximityManager().getDistance();
			  
        	sensorModeRGB.fetchSample(sampleRGB, 0);
	        float r = sampleRGB[0];
	        float g = sampleRGB[1];
	        float b = sampleRGB[2];
	        
	        MColor colorNormalized = new MColor("", r, g, b).normalize();
	        MColor closestColor = closestColorFinder.getClosestColor(colors, colorNormalized);
	        
	        textLCD.drawString("Cube: " + testCount, 1, 1);
	        
	        if (distanceValue < 25) {
			    textLCD.drawString(closestColor.getColorName() + " cube detected:", 1, 2);
	        } else if (distanceValue > 25 && distanceValue < 100) {
	        	textLCD.drawString("distant cube detected:", 1, 2);
	        } else {
	        	textLCD.drawString("nothing detected:", 1, 2);
	        }
	        
		    textLCD.drawString(""  + ", " +  colorNormalized.getRed(), 1, 3);
		    textLCD.drawString(""  + ", " +  colorNormalized.getGreen(), 1, 4);
		    textLCD.drawString(""  + ", " +  colorNormalized.getBlue(), 1, 5);
		    textLCD.drawString(""  + "" +  distanceValue, 1, 6);	
	        
        	testCount++;
        	
        	Delay.msDelay(250);
	        
	        if (testCount > 150) {
	        	break;
	        }
	    }  

	    return true;
	}
}
