package behaviour.modules.general;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;

public class CalibratieModule extends BehaviourModule {

	public CalibratieModule(Marvin marvin) {
		super(marvin);

	}

	@Override
	public boolean execute() {
		getMarvin().getColorSensorControlDown().calibrateSensor();
		return true;
	}
}
