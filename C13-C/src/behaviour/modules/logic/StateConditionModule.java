package behaviour.modules.logic;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.MarvinState;

public class StateConditionModule extends BehaviourModule {
	private MarvinState requiredState;
	
	public StateConditionModule(Marvin marvin, MarvinState requiredState) {
		super(marvin);
		this.requiredState = requiredState;
	}

	@Override
	public boolean execute() {
		return getMarvin().state == requiredState;
	}
}
