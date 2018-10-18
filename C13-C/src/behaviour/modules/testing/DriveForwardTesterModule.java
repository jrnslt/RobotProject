package behaviour.modules.testing;

import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import nl.hva.miw.robot.cohort13.HardwareInterface;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class DriveForwardTesterModule extends TestModule implements HardwareInterface {


	
	//Marvin marvin;

	
	public DriveForwardTesterModule(Marvin marvin, String testModuleName) {
		super(marvin, testModuleName);
	}

	@Override
	public boolean execute() {
		testDriveForward();
		//driveSlow();
		//driveFast()
		//stopRobot();
		
		return true;
	}
	
	public void testDriveForward() {
		
		marvin.GROTE_MOTOR_LINKS.forward();
		marvin.GROTE_MOTOR_RECHTS.forward();
		marvin.GROTE_MOTOR_LINKS.setSpeed(300);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(300);
	
		Delay.msDelay(5000);
	
		stopRobot();

		Delay.msDelay(1000);

		rotateLeft() ;
		Delay.msDelay(5000);
		
		stopRobot();
		Delay.msDelay(1000);

		rotateRight();
		Delay.msDelay(5000);
		stopRobot();

		Delay.msDelay(1000);


		rotateLeftCentered();
		
		Delay.msDelay(5000);

		rotateRightCentered();
		
		Delay.msDelay(5000);

	
		stopRobot();
		
		
		
	}

	public void driveSlow() {
		
		marvin.GROTE_MOTOR_LINKS.setSpeed(30);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(30);
		
	}

	public void driveFast() {
		
		marvin.GROTE_MOTOR_LINKS.setSpeed(800);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(800);

	}

	public void stopRobot() {
		
		
		marvin.GROTE_MOTOR_LINKS.stop();
		marvin.GROTE_MOTOR_RECHTS.stop();

	}

	
	public void goLeft() {
		marvin.GROTE_MOTOR_LINKS.forward();
		marvin.GROTE_MOTOR_RECHTS.forward();
		marvin.GROTE_MOTOR_LINKS.setSpeed(200);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(400);
	}


	public void goRight() {
		marvin.GROTE_MOTOR_LINKS.forward();
		marvin.GROTE_MOTOR_RECHTS.forward();
		marvin.GROTE_MOTOR_LINKS.setSpeed(400);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(200);
	}
	
	
	public void rotateLeft() {
		marvin.GROTE_MOTOR_LINKS.forward();
		marvin.GROTE_MOTOR_RECHTS.forward();
		marvin.GROTE_MOTOR_LINKS.setSpeed(10);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(300);
		
	}
	public void rotateRight() {
		marvin.GROTE_MOTOR_LINKS.forward();
		marvin.GROTE_MOTOR_RECHTS.forward();
		marvin.GROTE_MOTOR_LINKS.setSpeed(300);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(10);
	
	}
	
	public void rotateFastLeft() {
		marvin.GROTE_MOTOR_LINKS.setSpeed(0);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(800);
}
	
	public void rotateFastRight() {
		marvin.GROTE_MOTOR_LINKS.setSpeed(800);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(0);
	
	}
	
	public void rotateLeftCentered() {
		marvin.GROTE_MOTOR_LINKS.backward();
		marvin.GROTE_MOTOR_LINKS.setSpeed(800);
		marvin.GROTE_MOTOR_RECHTS.forward();
		marvin.GROTE_MOTOR_RECHTS.setSpeed(800);
		
	}
	
	
	public void rotateRightCentered() {
		marvin.GROTE_MOTOR_RECHTS.backward();
		marvin.GROTE_MOTOR_RECHTS.setSpeed(800);
		marvin.GROTE_MOTOR_LINKS.forward();
		marvin.GROTE_MOTOR_LINKS.setSpeed(800);
		
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
	
	/*
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
	*/
	



