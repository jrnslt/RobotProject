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
	private RegulatedMotor bigMotorLeft; 
	private RegulatedMotor bigMotorRight;
	private RegulatedMotor smallMotor_;
		
	public MotorControl(Marvin marvin) {
		super(marvin);
		
		bigMotorLeft = Motor.A;
		bigMotorRight = Motor.B;
		smallMotor_ = Motor.D;
	}

	public void rotate90Right () {
        long startTime180 = System.currentTimeMillis();
        long lastTime180 = System.currentTimeMillis();
        
        while (lastTime180 - startTime180 <1000) {
            lastTime180 = System.currentTimeMillis();
            
            bigMotorLeft.setSpeed(400);
            bigMotorLeft.forward();
            bigMotorRight.setSpeed(400);
            bigMotorRight.backward();
            
        }
        bigMotorLeft.stop();
        bigMotorRight.stop();
    }
    
    public void rotate90Left () {
        long startTime180 = System.currentTimeMillis();
        long lastTime180 = System.currentTimeMillis();
        
        while (lastTime180 - startTime180 <2000) {
            lastTime180 = System.currentTimeMillis();
            bigMotorLeft.setSpeed(200);
            bigMotorLeft.backward();
            bigMotorRight.setSpeed(200);
            bigMotorRight.forward();
        }
        bigMotorLeft.stop();
        bigMotorRight.stop();
    }
    
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
	
	public void rotateClockwise(int amount, int delay) {
		bigMotorLeft.stop();
		bigMotorRight.stop();
		
		Delay.msDelay(500);
		
		bigMotorLeft.setSpeed(amount);
        bigMotorLeft.forward();
        
        bigMotorRight.setSpeed(amount); 
        bigMotorRight.backward();
       
        Delay.msDelay(delay);
        
		bigMotorLeft.stop();
		bigMotorRight.stop();
	}
	
	public void rotate360DegreesClockwise () {
		rotateClockwise(800, 2500);
	}
	
	public void rotate225DegreesClockwise () {
        rotateClockwise(300, (int)(1.25f * 3500f));
	}
	
	public void rotate180DegreesClockwise () {
		rotateClockwise(300, 3500);
	}
	
	public void rotate180_2_Clockwise () {
		rotateClockwise(200, 4400);
	}
	
	public void rotate90DegreesClockwise () {
		rotateClockwise(200, 2000);
	}
	
	public void rotateCounterClockwise(int amount, int delay) {
		bigMotorLeft.stop();
		bigMotorRight.stop();
		
		bigMotorLeft.setSpeed(amount);
        bigMotorLeft.backward();
        
        bigMotorRight.setSpeed(amount); 
        bigMotorRight.forward();
       
        Delay.msDelay(delay);
        
		bigMotorLeft.stop();
		bigMotorRight.stop();
	}
	
	public void rotate360DegreesCounterClockwise () {
		rotateCounterClockwise(800, 2000);
	}
	
	public void rotate90DegreesCounterClockwise () {
		rotateCounterClockwise(200, 2000);
	}
	
	public void rotate45DegreesCounterClockwise () {
		rotateCounterClockwise(300, 875);
	}
	
	public void drive(int speedLeft, int speedRight) {
		Delay.msDelay(50);
		
		if (speedLeft > 0) {
			bigMotorLeft.setSpeed(speedLeft);
			bigMotorLeft.forward();
		} else if (speedLeft < 0) {
			bigMotorLeft.setSpeed(speedLeft);
			bigMotorLeft.backward();
		}
		
		if (speedRight > 0) {
			bigMotorRight.setSpeed(speedRight);
			bigMotorRight.forward();
		} else if (speedRight < 0) {
			bigMotorRight.setSpeed(speedRight);
			bigMotorRight.backward();
		}
	}
	
	public void driveForward(int speedLeft, int speedRight) {
		driveForward(speedLeft, speedRight, 0);
	}
	
	public void driveForward(int speedLeft, int speedRight, int delayMs) {
		bigMotorLeft.setSpeed(speedLeft);
		bigMotorRight.setSpeed(speedRight);
		bigMotorLeft.forward();
		bigMotorRight.forward();
		Delay.msDelay(delayMs);
	}
	
	public void driveBackwards(int speedLeft, int speedRight) {
		driveBackwards(speedLeft, speedRight, 0);
	}
	
	public void driveBackwards(int speedLeft, int speedRight, int delayMs) {
		bigMotorLeft.setSpeed(speedLeft);
		bigMotorRight.setSpeed(speedRight);
		bigMotorLeft.backward();
		bigMotorRight.backward();
		
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
		smallMotor_.setSpeed(speed);
		smallMotor_.backward();
		Delay.msDelay(delayMs);
		smallMotor_.stop();
	}
	
	public void letLoose() {
		letLoose(150, 1900);
	}
	
	public void letLoose(int speed, int delayMs) {
		smallMotor_.setSpeed(speed);
		smallMotor_.forward();
		Delay.msDelay(delayMs);
		smallMotor_.stop();
	}
}
	


