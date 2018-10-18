package parcours;

import behaviour.modules.BehaviourModule;
import behaviour.modules.testing.DriveForwardTesterModule;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.ZwartEnWit;

public class ParcoursModule extends BehaviourModule {
	
	public ParcoursModule(Marvin marvin) {
		super(marvin);
	}

	public boolean execute() {
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		EV3ColorSensor colorSensor = marvin.colorSensorA;
		SensorMode sensorModeRed = colorSensor.getRedMode();
		colorSensor.setFloodlight(Color.RED);
		colorSensor.setCurrentMode(sensorModeRed.getName());
		float[] sampleRed = new float[sensorModeRed.sampleSize()];	
		TextLCD textLCD = marvin.getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
		Sound.beep();
		startDrive();
		
		while (lastTime - startTime < 160000) {
			lastTime = System.currentTimeMillis();
			sensorModeRed.fetchSample(sampleRed, 0);
			textLCD.refresh();
			textLCD.clear();
			
			float r = sampleRed[0]; // rood
			
			if (r < 0.10) {
				System.out.printf("Kleur is \nzwart %.3f ", r);
				goMoreLeft();
		        Delay.msDelay(100);

			}   else if (r < 0.25) {
		        	goLeft();
			} else if (r < 0.35) {
				System.out.printf("Kleur is \n zwart - wit %.3f ", r);
				driveForward();
		        Delay.msDelay(100);

			} else if (r < 0.50) {
				
				goRight();
				Delay.msDelay(100);

			} else if (r > 0.50) {
				System.out.printf("Kleur is \n wit %.3f ", r);
				goMoreRight();
		        Delay.msDelay(100);

			} else if (r > 0.8) {
				 rotateLeftCentered();
				 Delay.msDelay(100);
			}
		}
			
		waitForKey(Button.ENTER);
		return true;	
	}

	public void startDrive() {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(200);
		marvin.groteMotorRechts.setSpeed(200);
	}
	

	public void driveForward()  {
	marvin.groteMotorLinks.forward();
	marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(200);
		marvin.groteMotorRechts.setSpeed(200);
	}
	
	
	public void goLeft() {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(50);
		marvin.groteMotorRechts.setSpeed(200);
	}
	
	public void goMoreLeft() {

		marvin.groteMotorLinks.backward();
		marvin.groteMotorLinks.setSpeed(100);
		marvin.groteMotorRechts.forward();
		marvin.groteMotorRechts.setSpeed(100);
		
	}
	

	
	
	public void goRight() {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.backward();
		marvin.groteMotorLinks.setSpeed(200);
		marvin.groteMotorRechts.setSpeed(50);
	}

	public void goMoreRight() {

		marvin.groteMotorLinks.forward();
		marvin.groteMotorLinks.setSpeed(100);
		marvin.groteMotorRechts.backward();
		marvin.groteMotorRechts.setSpeed(100);
	}
	
	
//	public void goExtremeRight() {
//		marvin.groteMotorLinks.forward();
//		marvin.groteMotorLinks.setSpeed(200);
//		marvin.groteMotorRechts.backward();
//		marvin.groteMotorRechts.setSpeed(200);
//	}

	public void stopRobot() {
		marvin.groteMotorLinks.stop();
		marvin.groteMotorRechts.stop();

	}
	
	public void rotateLeftCentered() {
		marvin.groteMotorLinks.backward();
		marvin.groteMotorLinks.setSpeed(800);
		marvin.groteMotorRechts.forward();
		marvin.groteMotorRechts.setSpeed(800);
		
	}

	public void waitForKey(Key key) {
		while(key.isUp()) {
			Delay.msDelay(100);
		}
		while(key.isDown()) {
			Delay.msDelay(100);
		}
	}
	
}
//	public boolean execute() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	}
//		

		
	
//		
//		
//		ZwartEnWit testerZW = new ZwartEnWit();
//		DriveForwardTesterModule testrij = new DriveForwardTesterModule(marvin, "Rijen");
//		
//		int gedrag = testerZW.test(marvin);
//		
//		while (gedrag != 0) {
//			if (gedrag == 3) {
//				testrij.testDriveForward();
//			} else if (gedrag == 2) {
//				testrij.goLeft();
//			}
//			else if (gedrag == 1) {
//				testrij.goRight();
//			}
//			
//			else {
//				testrij.driveFast();
//			}
//		
//		}
		
		// TODO Auto-generated method stub
		//return false;
	
