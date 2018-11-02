package behaviour.modules.procedures.keuze_opdracht;

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
import nl.hva.miw.robot.cohort13.functionality.MotorControl;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class KeuzeOpdrachtModule extends BehaviourModule {
	private final long runTime = 30000;
	private EV3ColorSensor colorSensor;
	
	public KeuzeOpdrachtModule(Marvin marvin) {
		super(marvin);
		colorSensor = marvin.getColorSensorControlFront().getColorSensor();
	}
	
	@Override
	public boolean execute() {    	
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		long startTime = System.currentTimeMillis();
		long currentTime = System.currentTimeMillis();
		MotorControl motorControl = getMarvin().getMotorControl();
		ClosestColorFinder closestColorFinder = getMarvin().getClosestColorFinder();
		
	    SensorMode sensorModeRGB = colorSensor.getRGBMode();   
	    float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
		colorSensor.setFloodlight(Color.WHITE);
		colorSensor.setCurrentMode(sensorModeRGB.getName());
		
		ArrayList<MColor> colors = new ArrayList<>();
		colors.add(Colors.BLUE);
		colors.add(Colors.ORANGE);
		colors.add(Colors.RED);
		colors.add(Colors.GREEN);
		motorControl.driveForward(150, 150);
		
		while(currentTime - startTime < 30000) {
			currentTime = System.currentTimeMillis();

		    int distanceValue = getMarvin().getProximityControl().getDistance();
		  
        	sensorModeRGB.fetchSample(sampleRGB, 0);
	        float r = sampleRGB[0];
	        float g = sampleRGB[1];
	        float b = sampleRGB[2];
	        
	        MColor colorNormalized = new MColor("", r, g, b).normalize();
	        MColor closestColor = closestColorFinder.getClosestColor(colors, colorNormalized);
		    
		    textLCD.refresh();
	        textLCD.clear();
	        textLCD.drawString("Distance:", 2, 1);
	        textLCD.drawString("" + distanceValue, 1, 2);
	        textLCD.drawString("" + closestColor.getColorName(), 2, 3);
	        Delay.msDelay(500);	
	       
	        if (distanceValue < 15 && closestColor == Colors.BLUE) {
	        	motorControl.stop();
	    		getMarvin().getMotorControl().driveBackwards(150, 150);
	    		Delay.msDelay(500);
	        	motorControl.stop();
	        	getMarvin().getMotorControl().drive(300, -300);
	        	Delay.msDelay(3000);
	        	motorControl.stop();
	    		getMarvin().getMotorControl().driveBackwards(150, 150);
	    		Delay.msDelay(500);
	        	motorControl.grabItForward(150, 800);
	        	getMarvin().getMotorControl().driveBackwards(150, 150);
	        	Delay.msDelay(2000);
	        	motorControl.grabItForward(150, 1200);
		        motorControl.stop();
		        Delay.msDelay(3000);
		        motorControl.letLoose();

	        }
		}
		
		return true;
	}  
}
	
	
