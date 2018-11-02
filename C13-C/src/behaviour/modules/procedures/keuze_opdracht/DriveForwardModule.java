package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class DriveForwardModule extends BehaviourModule {

	public DriveForwardModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		getMarvin().getMotorControl().drive(200, 200);
		Delay.msDelay(2000);
		return true;
	}

}
