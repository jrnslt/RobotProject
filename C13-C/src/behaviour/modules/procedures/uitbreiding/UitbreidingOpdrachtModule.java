package behaviour.modules.procedures.uitbreiding;

import java.util.ArrayList;

import behaviour.modules.BehaviourModule;
import behaviour.modules.procedures.keuze_opdracht.DropCubeModule;
import behaviour.modules.procedures.keuze_opdracht.GrabCubeModule;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.ColorSensorControl;
import nl.hva.miw.robot.cohort13.functionality.MemoryOpdracht2;
import nl.hva.miw.robot.cohort13.functionality.MotorControl;
import nl.hva.miw.robot.cohort13.functionality.ProximityControl;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class UitbreidingOpdrachtModule extends BehaviourModule {

	private boolean hasCube;
	private int countCubes;
	private boolean doContinue = true;
	private final long runTime = 10000000;

	public UitbreidingOpdrachtModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();

		hasCube = false;
		countCubes = 0;

// kleurenSensor
		ColorSensorControl colorSensorControlDown = getMarvin().getColorSensorControlDown();
		ColorSensorControl colorSensorControlFront = getMarvin().getColorSensorControlFront();
		colorSensorControlDown.calibrateSensor();
		colorSensorControlFront.calibrateSensor();
		Delay.msDelay(5000);

		EV3ColorSensor colorSensor = colorSensorControlDown.getColorSensor();
		EV3ColorSensor colorSensor2 = colorSensorControlFront.getColorSensor();

		SensorMode sensorModeRGB = colorSensor.getRGBMode();
		colorSensor.setFloodlight(Color.WHITE);
		colorSensor.setCurrentMode(sensorModeRGB.getName());

		SensorMode sensorModeRGB2 = colorSensor2.getRGBMode();
		colorSensor2.setFloodlight(Color.WHITE);
		colorSensor2.setCurrentMode(sensorModeRGB2.getName());

		float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
		float[] sampleRGB2 = new float[sensorModeRGB2.sampleSize()];

		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
		Sound.beep();

		ProximityControl afstandTester = getMarvin().getProximityControl(); // AfstandsSensor

		ArrayList<MColor> colors = new ArrayList<>();
		colors.add(Colors.RED);
		colors.add(Colors.GREEN);
		// colors.add(Colors.BLUE_GREY);
		// colors.add(Colors.ORANGE);
		colors.add(Colors.WHITE);
		colors.add(Colors.BLACK);
		colors.add(Colors.BLUE);
		colors.add(Colors.DARK_BLUE);

		while (lastTime - startTime < runTime) { // als alle 5 voorwerpen gebracht zijn dan uit de while loop countCubes == 5

			int distanceValue = afstandTester.getDistance();

			sensorModeRGB.fetchSample(sampleRGB, 0); // down
			sensorModeRGB2.fetchSample(sampleRGB2, 0); // front

			float r = sampleRGB[0]; // rood
			float g = sampleRGB[1]; // groen
			float b = sampleRGB[2]; // blauw

			r = colorSensorControlDown.getRed(r);
			g = colorSensorControlDown.getGreen(g);
			b = colorSensorControlDown.getBlue(b);

			MColor closestColorDown = getMarvin().getClosestColorFinder().getClosestColor(colors,
					new MColor("", r, g, b));

			
			float r2 = sampleRGB2[0]; // rood
			float g2 = sampleRGB2[1]; // groen
			float b2 = sampleRGB2[2]; // blauw

			r2 = colorSensorControlFront.getRed(r2);
			g2 = colorSensorControlFront.getGreen(g2);
			b2 = colorSensorControlFront.getBlue(b2);
			GrabCubeModule grabCube = new GrabCubeModule(getMarvin());
			DropCubeModule dropCube = new DropCubeModule(getMarvin());

	        MColor closestColorFront = getMarvin().getClosestColorFinder().
        		getClosestColor(colors, new MColor("", r2, g2, b2));
	        MColor colorBlockFront = getMarvin().getClosestColorFinder().
	        		getClosestColor(colors, new MColor("", r2, g2, b2));;
	        
			textLCD.setAutoRefresh(false);
			textLCD.refresh();
			textLCD.clear();
//	        textLCD.drawString("Distance:", 2, 1);
//	        textLCD.drawString("" + distanceValue, 1, 2);
//	        Delay.msDelay(500);

			textLCD.drawString("" + closestColorDown.getColorName(), 1, 6);

			textLCD.drawString("RGB mode", 1, 1);
			textLCD.drawString("" + r, 1, 2);
			textLCD.drawString("" + g, 1, 3);
			textLCD.drawString("" + b, 1, 4);
			textLCD.drawString(closestColorDown.getColorName(), 1, 5);

			MotorControl motorControl = getMarvin().getMotorControl();
			MemoryOpdracht2 memory = getMarvin().getMemoryOpdracht2();

			motorControl.driveForward(200, 200);

			if (closestColorDown == Colors.BLACK) {
				// Draaien
				motorControl.drive(-250, 250);
				Delay.msDelay(3000);
				motorControl.stop();
			}

			else if (distanceValue < 20) { // && closestColorFront == Colors.BLUE ) {
			
				
				motorControl.stop();
				Delay.msDelay(300);

				getMarvin().getMotorControl().letLoose(200, 1500);
				Delay.msDelay(300);
				colorBlockFront = getMarvin().getClosestColorFinder().
		        		getClosestColor(colors, new MColor("", r2, g2, b2));;

				grabCube.execute();
//	        	driveBackwardsandTurn();

//	        	getMarvin().getMotorControl().grabItForward(200, 1700);

				motorControl.stop();
//				motorControl.drive(200,200);
				hasCube = true;
//				countCubes++;
				// telt aantal cubes en welke kleur
			}

			while (hasCube) {
				motorControl.driveForward(100, 100);
				Delay.msDelay(350);

				if (closestColorDown == Colors.BLACK) {
					// Draaien
					motorControl.drive(-250, 250);
					Delay.msDelay(3000);
					motorControl.stop();
				}

				else if (closestColorDown == Colors.DARK_BLUE) {
					motorControl.driveForward(200, 200);
					Delay.msDelay(250);
					motorControl.rotate90DegreesCounterClockwise();
					motorControl.stop();
					motorControl.drive(250, 250);
					
					closestColorDown = getMarvin().getClosestColorFinder().getClosestColor(colors,
							new MColor("", r, g, b));

						
					if (closestColorDown == Colors.RED && colorBlockFront == Colors.RED) {
						dropCube.execute();
						break;
						
				} else if (closestColorDown == Colors.GREEN && colorBlockFront == Colors.GREEN) {
					dropCube.execute();
					break;
				} else if (closestColorDown == Colors.BLUE && colorBlockFront == Colors.BLUE) {
					dropCube.execute();
					break;
				} else {	Sound.buzz();
					break;
				}
	
			}
		}
			
			
		}
		
		return true;
	
	}

}
//	public void driveBackwardsandTurn() {
//		getMarvin().getMotorControl().stop();
//		Delay.msDelay(500);
//		getMarvin().getMotorControl().driveBackwards(100, 100);
//		Delay.msDelay(1000);
//		getMarvin().getMotorControl().stop();
//		Delay.msDelay(500);
//		getMarvin().getMotorControl().drive(-100, 100);
//		Delay.msDelay(3000);
//		getMarvin().getMotorControl().stop();
//		Delay.msDelay(500);
//		getMarvin().getMotorControl().driveBackwards(100, 100);
//		Delay.msDelay(1000);
//		getMarvin().getMotorControl().stop();
//		Delay.msDelay(500);
//
//	}
//
//	public boolean isColor(MColor color) {
//	
//		}
//		
//	}
	

