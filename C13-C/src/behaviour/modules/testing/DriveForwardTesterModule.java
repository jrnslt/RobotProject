package behaviour.modules.testing;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import nl.hva.miw.robot.cohort13.HardwareInterface;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

/**
 * @author daniel
 *
 * Voert testprocedure aan voor het testen van motoren
 *
 */
public class DriveForwardTesterModule extends BehaviourModule implements HardwareInterface {

	public DriveForwardTesterModule(Marvin marvin, String testModuleName) {
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

		
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(300);
		marvin.groteMotorRechts.setSpeed(300);
	
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
		
		marvin.groteMotorLinks.setSpeed(30);
		marvin.groteMotorRechts.setSpeed(30);
		
	}

	public void driveFast() {
		

		marvin.groteMotorLinks.setSpeed(800);
		marvin.groteMotorRechts.setSpeed(800);



	}

	public void stopRobot() {
		
		marvin.groteMotorLinks.stop();
		marvin.groteMotorRechts.stop();


	}

	
	public void goLeft() {

		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(200);
		marvin.groteMotorRechts.setSpeed(400);

	}


	public void goRight() {

		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(400);
		marvin.groteMotorRechts.setSpeed(200);
	}
	
	
	public void rotateLeft() {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(10);
		marvin.groteMotorRechts.setSpeed(300);
		

	}
	public void rotateRight() {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(300);
		marvin.groteMotorRechts.setSpeed(10);
	
	}
	
	public void rotateFastLeft() {
		marvin.groteMotorLinks.setSpeed(0);
		marvin.groteMotorRechts.setSpeed(800);
}
	
	public void rotateFastRight() {
		marvin.groteMotorLinks.setSpeed(800);
		marvin.groteMotorRechts.setSpeed(0);
	
	}
	
	public void rotateLeftCentered() {
		marvin.groteMotorLinks.backward();
		marvin.groteMotorLinks.setSpeed(800);
		marvin.groteMotorRechts.forward();
		marvin.groteMotorRechts.setSpeed(800);
		
	}
	
	
	public void rotateRightCentered() {
		marvin.groteMotorRechts.backward();
		marvin.groteMotorRechts.setSpeed(800);
		marvin.groteMotorLinks.forward();
		marvin.groteMotorLinks.setSpeed(800);
		
	}
}


	



