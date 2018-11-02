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
import nl.hva.miw.robot.cohort13.resources.Colors;

public class RoamingModule extends GroundColorReaderModule {

	public RoamingModule(Marvin marvin) {
		super(marvin, "roaming", null, "");
	}

	@Override
	public boolean execute() {	
        ArrayList<MColor> colors = getColors();
        
		while (true) {
	        MColor closestColor = readColor(colors);
	        int count = 0;
	        MotorControl motorControl = getMarvin().getMotorControl();
	        MemoryOpdracht2 memory = getMarvin().getMemoryOpdracht2();
	        
	        if (closestColor == Colors.BLACK) {
	        	//Draaien
	        	motorControl.stop();
	        	motorControl.drive(-250, 250);
	        	Delay.msDelay(3000);
	        	motorControl.stop();
	        } else {
	        	
	        	//kleur checken
	        	if (!memory.redCubeDelivered && closestColor == Colors.TAPE_RED) {
	        		getMarvin().getMemoryOpdracht2().currentColor = Colors.TAPE_RED;
		        	motorControl.drive(150, 150);
					Delay.msDelay(400);
	        		motorControl.stop();
	        		return true;
	        	} else if (!memory.greenCubeDelivered && closestColor == Colors.TAPE_GREEN) {
	        		getMarvin().getMemoryOpdracht2().currentColor = Colors.TAPE_GREEN;
		        	motorControl.drive(150, 150);
					Delay.msDelay(400);
	        		motorControl.stop();
	        		return true;
	        	} else if (!memory.blueCubeDelivered && closestColor == Colors.TAPE_BLUE) {
	        		getMarvin().getMemoryOpdracht2().currentColor = Colors.TAPE_BLUE;
		        	motorControl.drive(150, 150);
					Delay.msDelay(400);
	        		motorControl.stop();
	        		return true;
	        	} else {
	        		if (readColor(colors) == Colors.WHITE) {
		        		while (readColor(colors) == Colors.WHITE) {
				        	motorControl.drive(150, 150);
							Delay.msDelay(100);
		        		}
	        		} else {  			
			        	motorControl.drive(150, 150);
						Delay.msDelay(100);
	        		}
	        		
	        	}
	        }
		}
	}

}
