package nl.hva.miw.robot.cohort13;

import lejos.hardware.Sound;

import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

public class DriveForwardTester {

	Marvin marvin;
	UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
	UnregulatedMotor motorC = new UnregulatedMotor(MotorPort.C);

	public void testDriveForward(Marvin marvin) {

		motorB.setPower(50);
		motorC.setPower(50);
		motorB.forward();
		motorC.forward();

	
		
		

//		motorB.stop();
//		motorC.stop();

	}

	public void driveSlow(Marvin marvin) {

		motorB.setPower(20);
		motorC.setPower(20);
		motorB.forward();
		motorC.forward();

//		motorB.stop();
//		motorC.stop();

	}

	public void driveFast(Marvin marvin) {

		motorB.setPower(100);
		motorC.setPower(100);
		motorB.forward();
		motorC.forward();

	}

	public void stopRobot(Marvin marvin) {
		motorB.stop();
		motorC.stop();
		Sound.beepSequenceUp();

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
