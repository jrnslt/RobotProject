package nl.hva.miw.robot.cohort13.functionality;

import java.util.ArrayList;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class CubeFinder {
	private Marvin marvin;
	
	public CubeFinder(Marvin marvin) {
		this.marvin = marvin;
	}
	
	public CubeFinder(Marvin marvin, String testModuleName) {
		this.marvin = marvin;
	}

	/**
	 * @return a color if a cube is in front of marvin, otherwise return null
	 * 
	 */
	public MColor getCube(EV3ColorSensor colorSensor) {
		MColor closestColor = getColor(colorSensor);
		
		EV3IRSensor afstandTester = marvin.proximitySensor;
		final SampleProvider sp = afstandTester.getDistanceMode();
		float [] sample = new float[sp.sampleSize()];
	    sp.fetchSample(sample, 0);	
	    int distanceValue = (int)sample[0];
	    
	    if (distanceValue < 10) {
	    	return closestColor;
	    }
		
		return null;
	}
	
	public MColor getSeenColor(EV3ColorSensor colorSensor) {
	    SensorMode sensorModeRGB = colorSensor.getRGBMode();   
	    float[] sampleRGB = new float[sensorModeRGB.sampleSize()];

		colorSensor.setFloodlight(Color.WHITE);
		colorSensor.setCurrentMode(sensorModeRGB.getName());	
		
		sensorModeRGB.fetchSample(sampleRGB, 0);
		
        float r = sampleRGB[0];
        float g = sampleRGB[1];
        float b = sampleRGB[2];
		
		return new MColor("", r, g, b);	
	}
	
	private MColor getColor(EV3ColorSensor colorSensor) {
		ArrayList<MColor> colors = new ArrayList<>();
		colors.add(Colors.BLUE);
		colors.add(Colors.ORANGE);
		colors.add(Colors.RED);
		colors.add(Colors.GREEN);
		
		ClosestColorFinder closestColorFinder = marvin.getClosestColorFinder();
		
	    SensorMode sensorModeRGB = colorSensor.getRGBMode();   
	    float[] sampleRGB = new float[sensorModeRGB.sampleSize()];

		colorSensor.setFloodlight(Color.WHITE);
		colorSensor.setCurrentMode(sensorModeRGB.getName());		

		sensorModeRGB.fetchSample(sampleRGB, 0);
		
        float r = sampleRGB[0];
        float g = sampleRGB[1];
        float b = sampleRGB[2];

        MColor normalizedColor = new MColor("", r, g, b).normalize();
		MColor closestColor = closestColorFinder.getClosestColor(
				colors, 
				normalizedColor.getRed(), 
				normalizedColor.getGreen(), 
				normalizedColor.getBlue());
		
		return closestColor;
	}
	
	
	
	
}
