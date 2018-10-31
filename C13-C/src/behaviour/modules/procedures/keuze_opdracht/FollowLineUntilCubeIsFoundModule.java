package behaviour.modules.procedures.keuze_opdracht;

import java.util.ArrayList;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.ColorSensorControl;
import nl.hva.miw.robot.cohort13.functionality.MemoryOpdracht2;
import nl.hva.miw.robot.cohort13.functionality.MotorControl;
import nl.hva.miw.robot.cohort13.functionality.ProximityControl;
import nl.hva.miw.robot.cohort13.resources.Colors;

/**
 * volg gekleurde lijn totdat kubus is gevonden
 * 
 * 
 * 
 */

public class FollowLineUntilCubeIsFoundModule extends BehaviourModule {
	public MColor color;
	
	public FollowLineUntilCubeIsFoundModule(Marvin marvin, MColor color) {
		super(marvin);
		this.color = color;
	}

	
	@Override
	public boolean execute() {
		ColorSensorControl colorSensorControl = getMarvin().getColorSensorControlDown();
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
		colors.add(Colors.BLUE_GREY);
		colors.add(Colors.ORANGE);
		colors.add(Colors.WHITE);
		colors.add(Colors.BLACK);

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

		MColor closestColor = getMarvin().getClosestColorFinder().getClosestColor(colors, new MColor("", r, g, b));
		textLCD.drawString("" + closestColor.getColorName(), 1, 6);
		textLCD.drawString("RGB mode", 1, 1);
		textLCD.drawString(closestColor.getColorName(), 1, 2);

		MotorControl motorControl = getMarvin().getMotorControl();
		MemoryOpdracht2 dropBlock = getMarvin().getMemoryOpdracht2();
		ProximityControl proximitySensor = getMarvin().getProximityManager();
		
			while (true) {
				if (proximitySensor.getDistance() < 10) {
					textLCD.setAutoRefresh(false);
					textLCD.refresh();
					textLCD.clear();
					textLCD.drawString("distance: " + proximitySensor.getDistance(), 1, 2);
					//return true;
				} 
				/*
				if (closestColor == Colors.BLACK) {
					// Draaien
					motorControl.stop();
					motorControl.drive(-250, 250);
					Delay.msDelay(3700);
					motorControl.stop();
				} else {
					if (closestColor == Colors.WHITE || closestColor == Colors.ORANGE) {
						textLCD.drawString("Niet op de lijn", 1, 3);
						getMarvin().getMotorControl().drive(150, -150); // rechts
						Delay.msDelay(400);
						getMarvin().getMotorControl().drive(200, 200);
						Delay.msDelay(400);
					} else if (closestColor == color) { // links
						textLCD.drawString("Op de lijn", 1, 3);
						getMarvin().getMotorControl().drive(-150, 170);
						Delay.msDelay(400);
						getMarvin().getMotorControl().drive(20, 1);
						Delay.msDelay(400);
						getMarvin().getMotorControl().drive(200, 200);
						Delay.msDelay(400);
					}
				} 	
				*/
			}
		}
	}
	

