package behaviour.modules.procedures.testing;

import java.util.ArrayList;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.ColorSensorControl;
import nl.hva.miw.robot.cohort13.resources.Colors;

/**
 * volg een gekleurde lijn
 *
 */
public class RodeLijnTester extends BehaviourModule {
	private final long runTime = 10000000;
	
	public RodeLijnTester(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		ColorSensorControl colorSensorControl = getMarvin().getColorSensorControlDown();
		
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		EV3ColorSensor colorSensor = colorSensorControl.getColorSensor();

		SensorMode sensorModeRGB = colorSensor.getRGBMode();
		colorSensor.setFloodlight(Color.WHITE);
		colorSensor.setCurrentMode(sensorModeRGB.getName());

		float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
		Sound.beep();
	
        ArrayList<MColor> colors = new ArrayList<>();
        colors.add(Colors.RED);
        colors.add(Colors.GREEN);
        colors.add(Colors.BLUE);
        colors.add(Colors.ORANGE);
        colors.add(Colors.WHITE);
        colors.add(Colors.BLACK);
        colors.add(Colors.GREY);

        getMarvin().getColorSensorControlDown().calibrateSensor();
        
		while (lastTime - startTime < runTime) {
			lastTime = System.currentTimeMillis();
			sensorModeRGB.fetchSample(sampleRGB, 0);
			
	        textLCD.setAutoRefresh(false);
	        textLCD.refresh();
	        textLCD.clear();

			float r = sampleRGB[0]; // rood
			float g = sampleRGB[1]; // groen
			float b = sampleRGB[2]; // blauw
			
			r = colorSensorControl.getRed(r);
			g = colorSensorControl.getGreen(g);
			b = colorSensorControl.getBlue(b);
			
	        MColor closestColor = getMarvin().getClosestColorFinder().
	        		getClosestColor(colors, new MColor("", r, g, b));
	        textLCD.drawString("" +  closestColor.getColorName(), 1, 6);
			
		    textLCD.drawString("RGB mode", 1, 1);
	        textLCD.drawString("" + r, 1, 2);
	        textLCD.drawString("" + g, 1, 3);
	        textLCD.drawString("" + b, 1, 4);  
	        
	        if (closestColor == Colors.WHITE) {
	        	textLCD.drawString("wit 123", 1, 5);
	        	getMarvin().getMotorControl().drive(150, -150); //rechts
	        	Delay.msDelay(400);
	        	getMarvin().getMotorControl().drive(200, 200);		
				Delay.msDelay(400);
	        } else if (closestColor == Colors.BLACK) {		//links
	        	textLCD.drawString("black 123", 1, 5);
	        	getMarvin().getMotorControl().drive(-150, 150);
	        	Delay.msDelay(400);
	        	getMarvin().getMotorControl().drive(200, 200);
				Delay.msDelay(400);
				
	        }  else if (closestColor == Colors.GREY) {		//rechtdoor
	        	getMarvin().getMotorControl().drive(200, 200);
	        	Delay.msDelay(400);       	
	        }
		}
		
		getMarvin().getMotorControl().stop();	//Stop robot
		
		return true;
	}

}
