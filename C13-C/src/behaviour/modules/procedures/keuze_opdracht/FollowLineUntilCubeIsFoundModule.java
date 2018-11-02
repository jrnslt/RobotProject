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

public class FollowLineUntilCubeIsFoundModule extends GroundColorReaderModule {
	
	
	public FollowLineUntilCubeIsFoundModule(Marvin marvin, MColor color, String naam) {
		super(marvin, "fol c: " + naam, color, naam);
	}

	
	@Override
	public boolean execute() {
		ColorSensorControl colorSensorControl = getMarvin().getColorSensorControlDown();
		EV3ColorSensor colorSensor = colorSensorControl.getColorSensor();
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();

		ArrayList<MColor> colors = getColors();

		MotorControl motorControl = getMarvin().getMotorControl();
		MemoryOpdracht2 dropBlock = getMarvin().getMemoryOpdracht2();
		ProximityControl proximitySensor = getMarvin().getProximityControl();
		
		MColor lastCheckedColor = null;
		
        long maxStuckTime = 12000;
        long timeStart = System.currentTimeMillis();
		
		while (true) {
			
			if (lastCheckedColor == null) {
				lastCheckedColor = readColor(colors);
			}
	        
			textLCD.setAutoRefresh(false);
			textLCD.refresh();
			textLCD.clear();
			textLCD.drawString("distance: " + proximitySensor.getDistance(), 1, 2);
			

			if (readColor(colors) == color) { // links
				timeStart = System.currentTimeMillis();
				
				while (readColor(colors) == color) {
					timeStart = System.currentTimeMillis();
					if (proximitySensor.getDistance() < 10) {
						return true;
					}
					getMarvin().getMotorControl().drive(-150, 150);
					Delay.msDelay(400);		
				}
				
				getMarvin().getMotorControl().drive(250, 250);
				Delay.msDelay(400);
			} else {
				
				while (readColor(colors) != color) {
					if (proximitySensor.getDistance() < 10) {
						return true;
					}
					
					getMarvin().getMotorControl().drive(150, -150); // rechts
					Delay.msDelay(100);	
					
					/*
					if (readColor(colors) == Colors.WHITE || readColor(colors) == Colors.BLACK) {
						getMarvin().getMotorControl().drive(150, -150); // rechts
						Delay.msDelay(100);	
					} else {
						Delay.msDelay(400);	
						getMarvin().getMotorControl().drive(150, -150); // rechts
					}
					*/
					if (System.currentTimeMillis() - timeStart > maxStuckTime) {
						getMarvin().getMemoryOpdracht2().roaming = true;
						return false;
					}
				}
			}
			
			if (System.currentTimeMillis() - timeStart > maxStuckTime) {
				getMarvin().getMemoryOpdracht2().roaming = true;
				return false;
			}
			
			if (proximitySensor.getDistance() < 10) {
				return true;
			}
				
			lastCheckedColor = readColor(colors);
		}
	}
}
	

