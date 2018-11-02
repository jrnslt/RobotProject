package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;

public class DriveBackwardsModule extends BehaviourModule {

	public DriveBackwardsModule(Marvin marvin) {
		super(marvin);

	}

	@Override
	public boolean execute() {
		getMarvin().getMotorControl().driveBackwards(200, 200, 1000);
		return true;
	}

}
