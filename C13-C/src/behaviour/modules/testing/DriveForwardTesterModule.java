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
		marvin.groteMotorLinks.setSpeed(100);
		marvin.groteMotorRechts.setSpeed(100);
		
	//	UnregulatedMotor motorB = getMarvin().UNREGULATED_MOTOR_B;
	//	UnregulatedMotor motorC = getMarvin().UNREGULATED_MOTOR_C;

	
		/*
		motorB.stop();
		motorC.stop();
		*/
	}

	public void driveSlow() {
		
		marvin.groteMotorLinks.setSpeed(30);
		marvin.groteMotorRechts.setSpeed(30);
		
//		UnregulatedMotor motorB = getMarvin().UNREGULATED_MOTOR_B;
//		UnregulatedMotor motorC = getMarvin().UNREGULATED_MOTOR_C;
//		
//		motorB.setPower(20);
//		motorC.setPower(20);
//		motorB.forward();
//		motorC.forward();
		/*
		motorB.stop();
		motorC.stop();
		*/
	}

	public void driveFast() {
		
		marvin.groteMotorLinks.setSpeed(300);
		marvin.groteMotorRechts.setSpeed(300);


//		UnregulatedMotor motorB = getMarvin().UNREGULATED_MOTOR_B;
//		UnregulatedMotor motorC = getMarvin().UNREGULATED_MOTOR_C;
//		
//		motorB.setPower(100);
//		motorC.setPower(100);
//		motorB.forward();
//		motorC.forward();
	}

	public void stopRobot() {
		
		
		marvin.groteMotorLinks.stop();
		marvin.groteMotorRechts.stop();
//		UnregulatedMotor motorB = getMarvin().UNREGULATED_MOTOR_B;
//		UnregulatedMotor motorC = getMarvin().UNREGULATED_MOTOR_C;
//		
//		motorB.stop();
//		motorC.stop();
//		Sound.beepSequenceUp();
	}

	
	public void goLeft() {
		marvin.groteMotorLinks.setSpeed(20);
		marvin.groteMotorRechts.setSpeed(100);
	}


	public void goRight() {
		marvin.groteMotorLinks.setSpeed(100);
		marvin.groteMotorRechts.setSpeed(20);
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
	



