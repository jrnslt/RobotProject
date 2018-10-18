package behaviour.modules.testing;

import lejos.hardware.Sound;

import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import nl.hva.miw.robot.cohort13.HardwareInterface;
import nl.hva.miw.robot.cohort13.Marvin;

public class DriveForwardTesterModule extends TestModule implements HardwareInterface {

	Marvin marvin;
	
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

	
		marvin.GROTE_MOTOR_LINKS.setSpeed(100);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(100);
		
	//	UnregulatedMotor motorB = getMarvin().UNREGULATED_MOTOR_B;
	//	UnregulatedMotor motorC = getMarvin().UNREGULATED_MOTOR_C;

	
		/*
		motorB.stop();
		motorC.stop();
		*/
	}

	public void driveSlow() {
		
		marvin.GROTE_MOTOR_LINKS.setSpeed(30);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(30);
		
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
		
		marvin.GROTE_MOTOR_LINKS.setSpeed(300);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(300);


//		UnregulatedMotor motorB = getMarvin().UNREGULATED_MOTOR_B;
//		UnregulatedMotor motorC = getMarvin().UNREGULATED_MOTOR_C;
//		
//		motorB.setPower(100);
//		motorC.setPower(100);
//		motorB.forward();
//		motorC.forward();
	}

	public void stopRobot() {
		
		
		marvin.GROTE_MOTOR_LINKS.stop();
		marvin.GROTE_MOTOR_RECHTS.stop();
//		UnregulatedMotor motorB = getMarvin().UNREGULATED_MOTOR_B;
//		UnregulatedMotor motorC = getMarvin().UNREGULATED_MOTOR_C;
//		
//		motorB.stop();
//		motorC.stop();
//		Sound.beepSequenceUp();
	}

	
	public void goLeft() {
		marvin.GROTE_MOTOR_LINKS.setSpeed(20);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(100);
	}


	public void goRight() {
		marvin.GROTE_MOTOR_LINKS.setSpeed(100);
		marvin.GROTE_MOTOR_RECHTS.setSpeed(20);
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
	



