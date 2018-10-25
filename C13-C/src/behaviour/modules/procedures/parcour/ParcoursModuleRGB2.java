package behaviour.modules.procedures.parcour;

import java.util.ArrayList;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.MColor;

public class ParcoursModuleRGB2 extends BehaviourModule {

	private final long runTime = 10000000;
	private final double fairlyBlack = 0.4;
	private final double regularBlack = 0.8;
	private final double blackWhite = 1.2;
	private final double regularWhite = 1.6;
	private final double extremeWhite = 1.77;
	private final double reallyBlue = 2.5;

	public ParcoursModuleRGB2(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		EV3ColorSensor colorSensor = marvin.colorSensorA;

		SensorMode sensorModeRGB = colorSensor.getRGBMode();
		colorSensor.setFloodlight(Color.WHITE);
		colorSensor.setCurrentMode(sensorModeRGB.getName());

		float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
		TextLCD textLCD = marvin.getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
		Sound.beep();
		startDrive();

		//set stopwatch to zero
		Stopwatch stopWatch = null;
		
		while (lastTime - startTime < runTime) {
			lastTime = System.currentTimeMillis();
			sensorModeRGB.fetchSample(sampleRGB, 0);
			
	        textLCD.setAutoRefresh(false);
	        textLCD.refresh();
	        textLCD.clear();

			float r = sampleRGB[0]; // rood
			float r2 = r * 10;
			float g = sampleRGB[1]; // groen
			float g2 = g * 10;
			float b = sampleRGB[2]; // blauw
			float b2 = b * 10;

			String sR = String.format("R: %.2f", r2);
	        String sG = String.format("G: %.2f", g2);
	        String sB = String.format("B: %.2f ", b2);
	    
	        MColor seenColorNormalized = new MColor("", r, g, b).normalize();

		    textLCD.drawString("RGB mode", 1, 1);
	        textLCD.drawString("" + seenColorNormalized.getRed(), 1, 2);
	        textLCD.drawString("" + seenColorNormalized.getGreen(), 1, 3);
	        textLCD.drawString("" + seenColorNormalized.getBlue(), 1, 4);  
	        
			// obv lichtIntensiteit wordt de koers bepaald
	        // de robot stopt als hij veel blauw ziet.
	        float f = 2f;
	      
			if (r2 > (1.0 * f)) {
				textLCD.drawString(String.format("W %.3f / %.3f", r2, b2), 1, 5);
				goMoreLeft();
				
				Delay.msDelay(100);
			} else if (r2 > (0.8 * f)) {
				//goLeft();
				go(-50, 200);
				
				textLCD.drawString(String.format("WWZ  %.3f / %.3f", r2, b2), 1, 5);

			} else if (r2 > (0.4 * f) && r2 <= (0.8 * f)) {
				textLCD.drawString(String.format("ZW  %.3f / %.3f", r2, b2), 1, 5);
				
				driveForward();
				Delay.msDelay(50);

			} else if (r2 > (0.2 * f)) {
				textLCD.drawString(String.format("ZZW  %.3f / %.3f", r2, b2), 1, 5);
				
				//goRight();
				go(200, -50);
				
				Delay.msDelay(100);

			} else if (r2 <= (0.2 * f)) {
				textLCD.drawString(String.format("Z  %.3f / %.3f", r2, b2), 1, 5);
				
				goMoreRight();
				Delay.msDelay(100);
			}

			float nr = seenColorNormalized.getRed();
			float ng = seenColorNormalized.getGreen();
			float nb = seenColorNormalized.getBlue();
			
			float avg = (nr + ng)/2f;
			
			if (stopWatch != null) {
				textLCD.drawString("" + stopWatch.elapsed(), 1, 6);
			}
			
			if (nb - avg > 0.1) {
				if (stopWatch == null) {
					stopWatch = new Stopwatch();
				} else if (stopWatch.elapsed() > 8000) {
					stopRobot();
					Sound.beep();
					
					textLCD.clear();
					textLCD.refresh();
					textLCD.drawString("" + stopWatch.elapsed(), 1, 1);
					Delay.msDelay(5000);
					
					return true;
				}
			}
		}
		
		stopRobot();
		return true;
	}
	
	public void startDrive() {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(200);
		marvin.groteMotorRechts.setSpeed(200);
	}

	public void driveForward() {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(200);
		marvin.groteMotorRechts.setSpeed(200);
	}

	public void go(int left, int right) {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(left);
		marvin.groteMotorRechts.setSpeed(right);
	}
	
	public void goLeft() {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(50);
		marvin.groteMotorRechts.setSpeed(200);
	}

	public void goMoreLeft() {

		marvin.groteMotorLinks.backward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(120);
		marvin.groteMotorRechts.setSpeed(100);

	}

	public void goRight() {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.backward();
		marvin.groteMotorLinks.setSpeed(200);
		marvin.groteMotorRechts.setSpeed(50);
	}

	public void goMoreRight() {

		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.backward();
		marvin.groteMotorLinks.setSpeed(100);
		marvin.groteMotorRechts.setSpeed(120);
	}

	public void stopRobot() {
		marvin.groteMotorLinks.stop();
		marvin.groteMotorRechts.stop();

	}

	public void rotateLeftCentered() {
		marvin.groteMotorLinks.backward();
		marvin.groteMotorLinks.setSpeed(800);
		marvin.groteMotorRechts.forward();
		marvin.groteMotorRechts.setSpeed(800);

	}

}
