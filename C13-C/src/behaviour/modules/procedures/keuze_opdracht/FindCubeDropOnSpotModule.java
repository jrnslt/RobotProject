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
import nl.hva.miw.robot.cohort13.functionality.MotorControl;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class FindCubeDropOnSpotModule extends BehaviourModule {
	ColorSensorControl colorSensorControlDown = getMarvin().getColorSensorControlDown();
	ColorSensorControl colorSensorControlFront = getMarvin().getColorSensorControlFront();
    MotorControl motorControl = getMarvin().getMotorControl();

	public FindCubeDropOnSpotModule(Marvin marvin) {
		super(marvin);
		// TODO Auto-generated constructor stub
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
		float distance = measureDistance();
		
		  ArrayList<MColor> colors = new ArrayList<>();
	        colors.add(Colors.RED);
	        colors.add(Colors.GREEN);
	        colors.add(Colors.BLUE_GREY);
	        colors.add(Colors.ORANGE);
	        colors.add(Colors.WHITE);
	        colors.add(Colors.BLACK);
	      
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
		        
		        
		        if (closestColor == Colors.BLACK) {
		        	//Draaien
		        	motorControl.stop();
		        	motorControl.drive(-250, 250);
		        	Delay.msDelay(3700);
		        	motorControl.stop();
		        } else {
		        	motorControl.driveForward(200, 200);
		        	Delay.msDelay(300);
		        	distance = measureDistance();
		        	
		        	if (distance < 100) {
		        		break;
		        	}
		        	
		        }    	//kleur checken
		        	
		        	
		        }
		
//		
//		float[] afstanden = new float[10000];
//		int index = 0;
//	 = 1000;
// 
//		while (distance > 500) {
//			makeTurn();
//		Delay.msDelay(150);
//		afstanden[index] = measureDistance();
//		index++;
		
//		if (index == 9999) {
//			index = 0;
//		}
//		
//		if (afstanden[index] < afstanden[index-1]) {
//			motorControl.driveForward(100, 100);
//    		Delay.msDelay(150);
//    		afstanden[index] = measureDistance();
//    		index++;
//
//			if (afstanden[index] < 100) {
//				Sound.beepSequenceUp();
//	    		Delay.msDelay(150);
//				break;
//			}
//		}
		
		
		
//		boolean objectFound = false;
//
//		
//		while (!objectFound) {
//		
//			distance = measureDistance();
//			if (distance < 250) {
//				motorControl.driveForward(100, 100);
//	    		Delay.msDelay(150);
//				distance = measureDistance();
//				Sound.beep();
//				
//				if (distance < 30) {
//					objectFound = true;
//					motorControl.stop();
//					
//				} 
//			}
//		}
		
		
		
	
		// TODO Auto-generated method stub
		return true;
	}

	public void makeTurn() {
		motorControl.driveForward(-100, 100);
		Delay.msDelay(150);


	}

	public float measureDistance() {
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		final int HALF_SECOND = 500;

//	long startTime = System.currentTimeMillis();
//	long currentTime = System.currentTimeMillis();
//	
//	while(currentTime - startTime < 10000) {
//		currentTime = System.currentTimeMillis();

		int distanceValue = getMarvin().getProximityManager().getDistance();

		textLCD.refresh();
		textLCD.clear();
		textLCD.drawString("Distance:", 2, 1);
		textLCD.drawString("" + distanceValue, 1, 2);
		Delay.msDelay(HALF_SECOND);
//	}

		return distanceValue;
	}
}
