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

public class RoamingModule extends BehaviourModule {

	public RoamingModule(Marvin marvin) {
		super(marvin);
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
        colors.add(Colors.DARK_BLUE);
      
		while (true) {
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
	        textLCD.drawString(closestColor.getColorName(), 1, 5);  
	        
	        MotorControl motorControl = getMarvin().getMotorControl();
	        MemoryOpdracht2 memory = getMarvin().getMemoryOpdracht2();
	        
	        if (closestColor == Colors.BLACK) {
	        	//Draaien
	        	motorControl.stop();
	        	motorControl.drive(-250, 250);
	        	Delay.msDelay(3700);
	        	motorControl.stop();
	        } else {
	        	//kleur checken
	        	if (!memory.redCubeFound && closestColor == Colors.RED) {
	        		motorControl.stop();
	        		//Delay.msDelay(2000);
	        		Sound.beep();
	        		//return true;
	        	} else if (!memory.greenCubeFound && closestColor == Colors.GREEN) {
	        		motorControl.stop();
	        		
	        		//return true;
	        	} else if (!memory.blueCubeFound && closestColor == Colors.BLUE_GREY) {
	        		motorControl.stop();
	        		//Delay.msDelay(2000);
	        		//return true;
	        	} else {
	        	
		        	motorControl.drive(200, 200);
					Delay.msDelay(200);
	        	}
	        }
		}
	}

}
