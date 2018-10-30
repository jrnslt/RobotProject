package behaviour.modules.procedures.parcour;

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
import nl.hva.miw.robot.cohort13.resources.Colors;

public class ParcoursModuleRgbCalibrate extends BehaviourModule {
	private final long runTime = 30000;


	public ParcoursModuleRgbCalibrate(Marvin marvin) {
		super(marvin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute() {
		ColorSensorControl colorSensorControl = getMarvin().getColorSensorControlDown();
		colorSensorControl.calibrateSensor();
		

		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		EV3ColorSensor colorSensor = colorSensorControl.getColorSensor();

		SensorMode sensorModeRGB = colorSensor.getRGBMode();
		colorSensor.setFloodlight(Color.WHITE);
		colorSensor.setCurrentMode(sensorModeRGB.getName());

		float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
	
		  ArrayList<MColor> colors = new ArrayList<>();
	        colors.add(Colors.RED);
	        colors.add(Colors.BLUE);
	        colors.add(Colors.WHITE);
	        colors.add(Colors.BLACK);

		while (lastTime - startTime < runTime) {
//			getMarvin().getMotorControl().getBigMotorLeft().forward();
//			getMarvin().getMotorControl().getBigMotorRight().forward();
			
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
	        
	        
	        float nr = colorSensorControl.getRed(r);
			float ng = colorSensorControl.getGreen(g);
			float nb = colorSensorControl.getBlue(b);
	        
	        MColor closestColor = getMarvin().getClosestColorFinder().
	        		getClosestColor(colors, new MColor("", nr, ng, nb));
	        
		    textLCD.drawString("RGB mode", 1, 1);
	        textLCD.drawString(sR, 1, 2);
	        textLCD.drawString(sG, 1, 3);
	        textLCD.drawString(sB, 1, 4);  
	        
	        
	        if (closestColor == Colors.RED) { 
		    		Sound.beep();
		        	textLCD.drawString("red 123", 1, 5);
					Delay.msDelay(100);
					getMarvin().getMotorControl().drive(150, 150); //Drive forward
					Delay.msDelay(100);

	        } else if (closestColor == Colors.WHITE) {
	        	textLCD.drawString(String.format("W %.3f / %.3f", r2, b2), 1, 5);
				getMarvin().getMotorControl().drive(-150, 150); //Get More Left
				Delay.msDelay(100);
	        } 	else if (closestColor == Colors.BLACK) {
	        	
	        }


 	        
	         else if (r2 > 1.7) {
	        	textLCD.drawString(String.format("W %.3f / %.3f", r2, b2), 1, 5);
				getMarvin().getMotorControl().drive(-150, 150); //Get More Left
				Delay.msDelay(100);
	        } else if (r2 > 1.4) {
	        	getMarvin().getMotorControl().drive(1, 150);	//Go Left
								Delay.msDelay(150);

				textLCD.drawString(String.format("WWZ  %.3f / %.3f", r2, b2), 1, 5);
	        	
	        } else if (r2 > 0.9) {
	        	textLCD.drawString(String.format("ZW  %.3f / %.3f", r2, b2), 1, 5);
							//	Delay.msDelay(100);

				getMarvin().getMotorControl().drive(150, 150); //Drive forward
				Delay.msDelay(100);
	        } else if (r2 > 0.5) {
	        	textLCD.drawString(String.format("Z  %.3f / %.3f", r2, b2), 1, 5);
//				
				getMarvin().getMotorControl().drive(150, 1); //Get More Right
				Delay.msDelay(100);
	        } else if (r2 < 0.5) {
	        	getMarvin().getMotorControl().drive(150, -150); //Get More Right
				Delay.msDelay(100);
				
	        }
			
		}
		getMarvin().getMotorControl().stop();
		return false;
	}

}
