package behaviour.modules.procedures.console;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.MarvinState;

/**
 * 
 * returns true if marvinstate matches given state
 * else returns false
 *
 */
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
