package nl.hva.miw.robot.cohort13;

import lejos.hardware.Sound;

import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

public class DriveForwardTester implements HardwareInterface {

	Marvin marvin;


	public void testDriveForward(Marvin marvin) {
		marvin.GROTE_MOTOR_LINKS.forward();
		marvin.GROTE_MOTOR_RECHTS.forward();
		
		
//		GROTE_MOTOR_2.forward();
//		motorC.setPower(50);
//		motorB.forward();
//		motorC.forward();

	
		
		

//		motorB.stop();
//		motorC.stop();

	}

	public void turnRight(Marvin marvin) {
		
		marvin.GROTE_MOTOR_LINKS.setSpeed(400);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(50);

//
//		motorB.setPower(20);
//		motorC.setPower(20);
//		motorB.forward();
//		motorC.forward();

//		motorB.stop();
//		motorC.stop();

	}
	
	
	public void turnLeft(Marvin marvin) {
		
		marvin.GROTE_MOTOR_LINKS.setSpeed(400);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(50);

		
	}
	
	public void driveFast(Marvin marvin) {

//		motorB.setPower(100);
//		motorC.setPower(100);
//		motorB.forward();
//		motorC.forward();

	}

	public void stopRobot(Marvin marvin) {
//		motorB.stop();
//		motorC.stop();
//		Sound.beepSequenceUp();

	}

}

//	public void gaVoort() {
//	
//	}
//
//	public void gaLinks() {
//		motorA.setPower(50);
//		motorD.setPower(50);
//		motorA.forward();
//		motorD.backward();
//	}
//
//	public void stop() {
//		motorA.stop();
//		motorD.stop();
//	}
