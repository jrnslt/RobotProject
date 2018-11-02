package behaviour.modules.procedures.parcour;

import behaviour.modules.BehaviourModule;

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

public class ParcourFinal extends BehaviourModule {

	private final long runTime = 1000000;
	private final double fairlyBlack = 0.4;
	private final double regularBlack = 0.8;
	private final double blackWhite = 1.2;
	private final double regularWhite = 1.6;
	private final double extremeWhite = 1.77;
	private final double reallyBlue = 2.5;

	public ParcourFinal(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		EV3ColorSensor colorSensor = getMarvin().getColorSensorControlDown().getColorSensor();
		Stopwatch stopWatch = null;
		ColorSensorControl colorSensorControl = getMarvin().getColorSensorControlDown();
		colorSensorControl.calibrateSensor();
		 ArrayList<MColor> colors = new ArrayList<>();
	        colors.add(Colors.RED);
	        colors.add(Colors.BLUE);
	        colors.add(Colors.WHITE);
	        colors.add(Colors.BLACK);
		
		SensorMode sensorModeRGB = colorSensor.getRGBMode();
		colorSensor.setFloodlight(Color.WHITE);
		colorSensor.setCurrentMode(sensorModeRGB.getName());

		float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
		
		Sound.beep();
		getMarvin().getMotorControl().drive(200, 200);	//Start drive
		int timesRed = 0;
		
		

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


			float nr = colorSensorControl.getRed(r);
			float ng = colorSensorControl.getGreen(g);
			float nb = colorSensorControl.getBlue(b);
			
			String sR = String.format("R: ", nr);
	        String sG = String.format("G: ", ng);
	        String sB = String.format("B: ", nb);
	        
	     
	       
	        
	        MColor closestColor = getMarvin().getClosestColorFinder().
	        		getClosestColor(colors, new MColor("", nr, ng, nb));
	        
		    textLCD.drawString("RGB mode", 1, 1);
	        textLCD.drawString(sR, 1, 2);
	        textLCD.drawString(sG, 1, 3);
	        textLCD.drawString(sB, 1, 4);  
			
	   
			

//	        if (closestColor == Colors.RED) {  // rood herkennen
//	    		Sound.beep();
//	        	textLCD.drawString("red 123", 1, 5);
//
//				Delay.msDelay(100);
//
//        }
//
//	         else if (r2 > 1.7) {
//	        	textLCD.drawString(String.format("W %.3f / %.3f", r2, b2), 1, 5);
//				getMarvin().getMotorControl().drive(-150, 150); //Get More Left
//				Delay.msDelay(150);
//	        } else if (r2 > 1.4) {
//	        	getMarvin().getMotorControl().drive(1, 150);	//Go Left
//								Delay.msDelay(150);
//
//				textLCD.drawString(String.format("WWZ  %.3f / %.3f", r2, b2), 1, 5);
//	        	
//	        } else if (r2 > 0.9) {
//	        	textLCD.drawString(String.format("ZW  %.3f / %.3f", r2, b2), 1, 5);
//							//	Delay.msDelay(100);
//
//				getMarvin().getMotorControl().drive(150, 150); //Drive forward
//				Delay.msDelay(150);
//	        } else if (r2 > 0.5) {
//	        	textLCD.drawString(String.format("Z  %.3f / %.3f", r2, b2), 1, 5);
//=======
	        if (closestColor == Colors.RED) { 
	        	
	        	timesRed++;
	    		Sound.beep();
	    		
	    		
	    		if (timesRed > 1) {
		        	textLCD.drawString("red 123", 1, 5);
					getMarvin().getMotorControl().stop();
	
					
					textLCD.drawString("tijd: " + stopWatch.elapsed() / 1000.0f, 1, 5); 
					Delay.msDelay(10000);
					break;
	    		} else {
	    			stopWatch = new Stopwatch();
	    			Delay.msDelay(1000);
	    		}
	        }

	        

	         else if (r2 > 1.4) {
	        	textLCD.drawString(String.format("W %.3f / %.3f", r2, b2), 1, 5);
				getMarvin().getMotorControl().drive(-100, 250); //Get More Left
				Delay.msDelay(250);
	        } else if (r2 > 1.2) {
	        	getMarvin().getMotorControl().drive(1, 200);	//Go Left
								Delay.msDelay(150);

				textLCD.drawString(String.format("WWZ  %.3f / %.3f", r2, b2), 1, 5);
	        	
	        } else if (r2 > 1.0) {
	        	textLCD.drawString(String.format("ZW  %.3f / %.3f", r2, b2), 1, 5);
							//	Delay.msDelay(100);

				getMarvin().getMotorControl().drive(300, 300); //Drive forward
				Delay.msDelay(150);
	        } else if (r2 > 0.6) {
	        	textLCD.drawString(String.format("Z  %.3f / %.3f", r2, b2), 1, 5);
//				
				getMarvin().getMotorControl().drive(200, 1); //Get More Right
				Delay.msDelay(150);
	        } else if (r2 < 0.6) {
	        	getMarvin().getMotorControl().drive(250, -100); //Get More Right
				Delay.msDelay(250);
//			
	        }
	        
	        
	        
//	        } else if (r2 < 0.8 && r2 > 0.10 && b2 < 0.7) {
//				getMarvin().getMotorControl().stop();
//				System.out.println("BLAUW! ");
//				Delay.msDelay(100);
//	        }
	        
	        	
	        
	        

//			if (r2 > 2.50 && g2> 2.80 && b2 > 3.7) {
//				textLCD.drawString(String.format("W %.3f / %.3f", r2, b2), 1, 5);
//				getMarvin().getMotorControl().drive(-120, 120); //Get More Left
//				Delay.msDelay(100);
//			} else if (r2 > 2.3 && g2> 2.40  && b2 > 3) {
//				getMarvin().getMotorControl().drive(50, 200);	//Go Left
//				
//				textLCD.drawString(String.format("WWZ  %.3f / %.3f", r2, b2), 1, 5);
//
//			} else if (r2 > 1.1 && g2 > 1.20 && b2 > 2) {
//				textLCD.drawString(String.format("ZW  %.3f / %.3f", r2, b2), 1, 5);
//				
//				getMarvin().getMotorControl().drive(200, 200); //Drive forward
//				Delay.msDelay(100);
//
//			} else if (r2 > 0.8  && g2 > 0.60 && b2 > 1 ) {
//				textLCD.drawString(String.format("ZZW  %.3f / %.3f", r2, b2), 1, 5);
//				
//				getMarvin().getMotorControl().drive(200, 50);	//Go Right	
//				Delay.msDelay(100);
//			} else if (r2 < 0.8  && g2 < 0.60 && b2 <  1.0) {
//				textLCD.drawString(String.format("Z  %.3f / %.3f", r2, b2), 1, 5);
//				
//				getMarvin().getMotorControl().drive(-120, 120); //Get More Right
//				Delay.msDelay(100);
//			}
//
//			else if (r2 > 1.10 && g2 > 1.5 && b2 > 1.50) {
//				getMarvin().getMotorControl().stop();
//				System.out.println("BLAUW! ");
//				Delay.msDelay(100);
//			}
		
//		if (stopWatch != null) {
//			textLCD.drawString("" + stopWatch.elapsed(), 1, 6);
//		}
//		
	      

		}
		getMarvin().getMotorControl().stop();
		
		return true;
	}
}