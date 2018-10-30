package nl.hva.miw.robot.cohort13.functionality;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

/**
 * Deze klasse regelt de motoraansturing
 *
 */
public class MotorControl extends MarvinComponent {
	
	public MotorControl(Marvin marvin) {
		super(marvin);
		bigMotorLeft = Motor.A;
		bigMotorRight = Motor.B;
		smallMotor_ = Motor.D;
	}

	private RegulatedMotor bigMotorLeft; 
	private RegulatedMotor bigMotorRight;
	private RegulatedMotor smallMotor_;
	

	
	public RegulatedMotor getBigMotorLeft() {
		return bigMotorLeft;
	}
	
	public RegulatedMotor getBigMotorRight() {
		return bigMotorRight;
	}
	
	public RegulatedMotor getSmallMotor() {
		return smallMotor_;
	}
	
	/*
	 * The driving motors
	 */
	public void rotate360Backward () {
		long startTime180 = System.currentTimeMillis();
		long lastTime180 = System.currentTimeMillis();
		
		while (lastTime180 - startTime180 < 6000) {
			lastTime180 = System.currentTimeMillis();
			bigMotorLeft.backward();
			bigMotorLeft.setSpeed(600);
			bigMotorRight.forward();
			bigMotorRight.setSpeed(600);
		}
	}

	
	public void rotate360Forward () {
		long startTime180 = System.currentTimeMillis();
		long lastTime180 = System.currentTimeMillis();
		
		while (lastTime180 - startTime180 < 6000) {
			lastTime180 = System.currentTimeMillis();
			bigMotorLeft.forward();
			bigMotorLeft.setSpeed(600);
			bigMotorRight.backward();
			bigMotorRight.setSpeed(600);
		}
	}

	
	public void rotate180 () {
		long startTime180 = System.currentTimeMillis();
		long lastTime180 = System.currentTimeMillis();
		
		while (lastTime180 - startTime180 < 3300) {
			lastTime180 = System.currentTimeMillis();
			bigMotorLeft.forward();
			bigMotorLeft.setSpeed(300);
			bigMotorRight.backward();
			bigMotorRight.setSpeed(300);
		}
	}
	
	public void drive(int speedLeft, int speedRight) {
		if (speedLeft > 0) {
			bigMotorLeft.forward();
			bigMotorLeft.setSpeed(speedLeft);
		} else if (speedLeft < 0) {
			bigMotorLeft.backward();
			bigMotorLeft.setSpeed(speedLeft);
		}
		
		if (speedRight > 0) {
			bigMotorRight.forward();
			bigMotorRight.setSpeed(speedRight);
		} else if (speedRight < 0) {
			bigMotorRight.backward();
			bigMotorRight.setSpeed(speedRight);
		}
	}
	
	public void driveForward(int speedLeft, int speedRight) {
		driveForward(speedLeft, speedRight, 0);
	}
	
	public void driveForward(int speedLeft, int speedRight, int delayMs) {
		bigMotorLeft.forward();
		bigMotorRight.forward();
		bigMotorLeft.setSpeed(speedLeft);
		bigMotorRight.setSpeed(speedRight);
		Delay.msDelay(delayMs);
	}
	
	public void driveBackwards(int speedLeft, int speedRight) {
		driveBackwards(speedLeft, speedRight, 0);
	}
	
	public void driveBackwards(int speedLeft, int speedRight, int delayMs) {
		bigMotorLeft.backward();
		bigMotorRight.backward();
		bigMotorLeft.setSpeed(speedLeft);
		bigMotorRight.setSpeed(speedRight);
		
		if (delayMs > 0) {
			Delay.msDelay(delayMs);
		}
	}
	
	public void stop() {
		bigMotorLeft.stop();
		bigMotorRight.stop();
	}
	/*
	 * The grabber
	 */
	public void grabIt() {
		grabItForward(200, 2200);
	}
	
	public void grabItForward(int speed, int delayMs) {
		smallMotor_.backward();
		smallMotor_.setSpeed(speed);
		Delay.msDelay(delayMs);
		smallMotor_.stop();
	}
	
	public void letLoose() {
		letLoose(150, 1900);
	}
	
	public void letLoose(int speed, int delayMs) {
		smallMotor_.forward();
		smallMotor_.setSpeed(speed);
		Delay.msDelay(delayMs);
		smallMotor_.stop();
	}
}
	


