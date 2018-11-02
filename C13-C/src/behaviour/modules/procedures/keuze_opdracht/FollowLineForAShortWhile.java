package behaviour.modules.procedures.keuze_opdracht;


import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;

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

public class FollowLineForAShortWhile extends GroundColorReaderModule {

	private MColor color;

	public FollowLineForAShortWhile(Marvin marvin, MColor color) {
		super(marvin, "follow for a while", color, "");
	}

	@Override
	public boolean execute() {
		ArrayList<MColor> colors = getColors();
		
        long timeStart = System.currentTimeMillis();
		
		while (true) {
			MColor closestColor = readColor(colors);
		
			
			if (System.currentTimeMillis() - timeStart > 4000) {
				return true;
			} else {
				if (closestColor != color) {	
					while (readColor(colors) != color) {
						
						if (readColor(colors) == Colors.BLACK || readColor(colors) == Colors.WHITE) {
							getMarvin().getMotorControl().drive(150, -150); // rechts
							Delay.msDelay(100);	
						} else {
							getMarvin().getMotorControl().drive(250, 250);
							Delay.msDelay(100);
							getMarvin().getMotorControl().drive(-150, 150);
							Delay.msDelay(400);		
						}
						
						if (System.currentTimeMillis() - timeStart > 4000) {
							return true;
						}
					}	
				} else if (closestColor == color) { // links
							
					while (readColor(colors) == color) {
						getMarvin().getMotorControl().drive(-150, 150);
						Delay.msDelay(400);	
						
						if (System.currentTimeMillis() - timeStart > 4000) {
							return true;
						}
					}
					
					getMarvin().getMotorControl().drive(300, 300);
					Delay.msDelay(400);
				}
			}
		}
	}
}
