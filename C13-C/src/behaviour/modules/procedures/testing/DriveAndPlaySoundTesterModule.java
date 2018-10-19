package behaviour.modules.procedures.testing;

import behaviour.modules.BehaviourModule;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class DriveAndPlaySoundTesterModule extends BehaviourModule {

	public DriveAndPlaySoundTesterModule(Marvin marvin, String testModuleName) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		testDriveForward();

		return true;
	}
	
	public void testDriveForward() {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(300);
		marvin.groteMotorRechts.setSpeed(300);
	
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
}


	
