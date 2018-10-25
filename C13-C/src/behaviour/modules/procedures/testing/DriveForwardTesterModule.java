package behaviour.modules.procedures.testing;

import behaviour.modules.BehaviourModule;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

/**
 *
 * Voert testprocedure aan voor het testen van motoren
 *
 */
public class DriveForwardTesterModule extends BehaviourModule {

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
		getMarvin().getMotorControl().drive(300, 300);
		
		Delay.msDelay(5000);
	
		getMarvin().getMotorControl().stop();

		Delay.msDelay(1000);

		getMarvin().getMotorControl().drive(10, 300); //Rotate left
		Delay.msDelay(5000);
		
		getMarvin().getMotorControl().stop();
		Delay.msDelay(1000);

		getMarvin().getMotorControl().drive(300, 10); //Rotate Right
		Delay.msDelay(5000);
		getMarvin().getMotorControl().stop();

		Delay.msDelay(1000);

		getMarvin().getMotorControl().drive(-800, 800); //Rotate left Centered

		Delay.msDelay(5000);

		getMarvin().getMotorControl().drive(800, -800); //Rotate Right Centered
		
		Delay.msDelay(5000);
		getMarvin().getMotorControl().stop();
	}
}


	



