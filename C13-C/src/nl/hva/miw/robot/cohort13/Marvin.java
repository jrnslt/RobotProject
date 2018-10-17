package nl.hva.miw.robot.cohort13;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;

import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Delay;

public class Marvin {
	
	Brick brick;

	public Marvin() {
		super(); 
		brick = LocalEV3.get();
	}
	
	public static void main(String[] args) {
		Marvin marvin = new Marvin();
	
		marvin.run();
	
		
		//marvin.startMotor();
//	
//		
//		marvin.startMotor();
		
		
		
	}
	
	private void run() {
		TextLCD display = brick.getTextLCD();
		display.drawString("Welkom", 0, 3);
		display.drawString("Team Charlie!", 0, 4);		
		
		
		
		
		DriveForwardTester driveForwardTester = new DriveForwardTester();
		driveForwardTester.testDriveForward(this);
		
//		driveForwardTester.driveFast(this);
//		driveForwardTester.driveSlow(this);
//		driveForwardTester.stopRobot(this);
		
		
		
		ColorSensorTester colorSensorTester = new ColorSensorTester();
//		colorSensorTester.test(this);
		
		Sound.buzz();
		Sound.twoBeeps(); 

		
		driveForwardTester.driveFast(this);
		Sound.twoBeeps(); 
		driveForwardTester.stopRobot(this);
		
		
		TouchSensorTester touchSensorTester = new TouchSensorTester();
	//	touchSensorTester.test(this);
			
		Sound.playTone(5, 10000);
	
		System.out.println("Test complete....");
		
		
		
		
		waitForKey(Button.ENTER);
	}
	
	public void waitForKey(Key key) {
		while(key.isUp()) {
			Delay.msDelay(100);
		}
		while(key.isDown()) {
			Delay.msDelay(100);
		}
	}
	
	
	
	
	public void startMotor() {
		
		UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
		UnregulatedMotor motorC = new UnregulatedMotor(MotorPort.C);
        motorB.setPower(100);
		motorC.setPower(50);
		
	    motorC.forward();
      motorB.forward();
		Sound.twoBeeps(); 
		
//		
//        UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
//        UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);
//        motorA.setPower(100);
//        motorD.setPower(100);
//        motorA.forward();
//        motorD.forward();
	
     

	}
	
	
	
}
