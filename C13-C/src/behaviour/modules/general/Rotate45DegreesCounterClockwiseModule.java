package behaviour.modules.general;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;

public class Rotate45DegreesCounterClockwiseModule extends BehaviourModule {

	public Rotate45DegreesCounterClockwiseModule(Marvin marvin) {
		super(marvin);

	}

	@Override
	public boolean execute() {
		getMarvin().getMotorControl().rotateCounterClockwise(300, 875);	
		return true;
	}

}
