package behaviour.modules.procedures.keuze_opdracht;

import java.util.ArrayList;

import behaviour.modules.BehaviourModule;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;

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
 * volg gekleurde lijn totdat gele vlak is gevonden. Dan wordt deze gedropt.
 * 
 */

public class FollowLineUntilDropSpotModule extends GroundColorReaderModule {

	public FollowLineUntilDropSpotModule(Marvin marvin, MColor color) {
		super(marvin, "follow and drop cube", color, "");
	}

	@Override
	public boolean execute() {
		ColorSensorControl colorSensorControl = getMarvin().getColorSensorControlDown();
		EV3ColorSensor colorSensor = colorSensorControl.getColorSensor();
		
		ArrayList<MColor> colors = getColors();

		MotorControl motorControl = getMarvin().getMotorControl();
		MemoryOpdracht2 dropBlock = getMarvin().getMemoryOpdracht2();
		ProximityControl proximitySensor = getMarvin().getProximityControl();
		
        long maxStuckTime = 12000;
        long timeStart = System.currentTimeMillis();
		
		while (true) {	
			MColor closestColor = readColor(colors);
			
			if (closestColor != color) {	
				
				while (readColor(colors) != color) {
					if (readColor(colors) == Colors.BLACK) {
						
						getMarvin().getMotorControl().drive(50, 50); 
						Delay.msDelay(100);	
						
						motorControl.stop();
						return true;
					} else {		
						getMarvin().getMotorControl().drive(150, -150); 
						Delay.msDelay(100);
						/*
						if (readColor(colors) == Colors.WHITE) {
							getMarvin().getMotorControl().drive(150, -150); 
							Delay.msDelay(100);		
						} else {
							Delay.msDelay(400);
							getMarvin().getMotorControl().drive(150, -150); 
						}
						*/
					}
					if (System.currentTimeMillis() - timeStart > maxStuckTime) {
						getMarvin().getMemoryOpdracht2().roaming = true;
						return false;
					}
				}
			} else if (closestColor == color) { // links
				timeStart = System.currentTimeMillis();
				
				while (readColor(colors) == color) {
					timeStart = System.currentTimeMillis();
					getMarvin().getMotorControl().drive(-150, 150);
					Delay.msDelay(400);		
				}
				
				getMarvin().getMotorControl().drive(250, 250);
				Delay.msDelay(400);
			}
		}
	}
}
