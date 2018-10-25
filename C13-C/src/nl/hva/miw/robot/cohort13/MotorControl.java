package nl.hva.miw.robot.cohort13;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;


public class MotorControl {

/*
 * Deze klasse regelt de motoraansturing
 *  	
 */
	public RegulatedMotor bigMotorLeft = Motor.A;
	public RegulatedMotor bigMotorRight = Motor.B;
	public RegulatedMotor SmallMotor = Motor.C;
		
	/*
	 * The driving motors
	 */
	
	public void driveForward (int speedLeft, int speedRight, int delayMs) {
		bigMotorLeft.forward();
		bigMotorRight.forward();
		bigMotorLeft.setSpeed(speedLeft);
		bigMotorRight.setSpeed(speedRight);
		Delay.msDelay(delayMs);
	}
	
	public void driveBackwards (int speedLeft, int speedRight, int delayMs) {
		bigMotorLeft.backward();
		bigMotorRight.backward();
		bigMotorLeft.setSpeed(speedLeft);
		bigMotorRight.setSpeed(speedRight);
		Delay.msDelay(delayMs);
	}
	/*
	 * The grabber
	 */
	
	public void grabItForward(int speed, int delayMs) {
		SmallMotor.backward();
		SmallMotor.setSpeed(speed);
		Delay.msDelay(delayMs);
		SmallMotor.stop();
	}
	
	public void letLoose(int speed, int delayMs) {
		SmallMotor.forward();
		SmallMotor.setSpeed(speed);
		Delay.msDelay(delayMs);
		SmallMotor.stop();
	}
}
	


