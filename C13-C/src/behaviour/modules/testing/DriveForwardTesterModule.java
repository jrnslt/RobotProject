package behaviour.modules.testing;

import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import nl.hva.miw.robot.cohort13.HardwareInterface;

import lejos.robotics.RegulatedMotor;
import nl.hva.miw.robot.cohort13.Marvin;

public class DriveForwardTesterModule extends TestModule implements HardwareInterface {


	public RegulatedMotor GROTE_MOTOR_LINKS;
	public RegulatedMotor GROTE_MOTOR_RECHTS;
	public RegulatedMotor KLEINE_MOTOR_ARM;
	public RegulatedMotor GROTE_MOTOR_4;
	
	//Marvin marvin;

	
	public DriveForwardTesterModule(Marvin marvin, String testModuleName) {
		super(marvin, testModuleName);
		
		GROTE_MOTOR_LINKS = Motor.A;
		GROTE_MOTOR_RECHTS = Motor.B;
		KLEINE_MOTOR_ARM = Motor.C;
		GROTE_MOTOR_4 = Motor.D;
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

		GROTE_MOTOR_LINKS.setSpeed(100);
		GROTE_MOTOR_RECHTS.setSpeed(100);

		
	//	UnregulatedMotor motorB = getMarvin().UNREGULATED_MOTOR_B;
	//	UnregulatedMotor motorC = getMarvin().UNREGULATED_MOTOR_C;

	
		/*
		motorB.stop();
		motorC.stop();
		*/
	}

	public void driveSlow() {
		
		GROTE_MOTOR_LINKS.setSpeed(30);
		GROTE_MOTOR_RECHTS.setSpeed(30);
		
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
		
		GROTE_MOTOR_LINKS.setSpeed(300);
		GROTE_MOTOR_RECHTS.setSpeed(300);


//		UnregulatedMotor motorB = getMarvin().UNREGULATED_MOTOR_B;
//		UnregulatedMotor motorC = getMarvin().UNREGULATED_MOTOR_C;
//		
//		motorB.setPower(100);
//		motorC.setPower(100);
//		motorB.forward();
//		motorC.forward();
	}

	public void stopRobot() {
		
		
		GROTE_MOTOR_LINKS.stop();
		GROTE_MOTOR_RECHTS.stop();
//		UnregulatedMotor motorB = getMarvin().UNREGULATED_MOTOR_B;
//		UnregulatedMotor motorC = getMarvin().UNREGULATED_MOTOR_C;
//		
//		motorB.stop();
//		motorC.stop();
//		Sound.beepSequenceUp();
	}

	
	public void goLeft() {
		GROTE_MOTOR_LINKS.setSpeed(20);
		GROTE_MOTOR_RECHTS.setSpeed(100);
	}


	public void goRight() {
		GROTE_MOTOR_LINKS.setSpeed(100);
		GROTE_MOTOR_RECHTS.setSpeed(20);
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
	



