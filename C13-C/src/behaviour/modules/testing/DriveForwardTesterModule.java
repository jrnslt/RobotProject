package behaviour.modules.testing;

import lejos.hardware.Sound;

import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import nl.hva.miw.robot.cohort13.Marvin;

public class DriveForwardTesterModule extends TestModule {

	private UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
	private UnregulatedMotor motorC = new UnregulatedMotor(MotorPort.C);
	
	public DriveForwardTesterModule(Marvin marvin) {
		super(marvin);
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
		motorB.setPower(50);
		motorC.setPower(50);
		motorB.forward();
		motorC.forward();
		/*
		motorB.stop();
		motorC.stop();
		*/
	}

	public void driveSlow() {

		motorB.setPower(20);
		motorC.setPower(20);
		motorB.forward();
		motorC.forward();
		/*
		motorB.stop();
		motorC.stop();
		*/
	}

	public void driveFast() {
		motorB.setPower(100);
		motorC.setPower(100);
		motorB.forward();
		motorC.forward();
	}

	public void stopRobot() {
		motorB.stop();
		motorC.stop();
		Sound.beepSequenceUp();
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
	
}


