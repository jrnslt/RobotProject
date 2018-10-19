package behaviour.modules.logic;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;



/**
 * simple module that has 1 submodule
 * returns the value of the submodule
 *
 */
public class LeafModule extends BehaviourModule {
	private BehaviourModule subModule;
	
	public LeafModule(Marvin marvin) {
		super(marvin);
	}
	
	public LeafModule addModule(BehaviourModule subModule) {
		this.subModule = subModule;
		return this;
	}

	@Override
	public boolean execute() {
		return subModule.execute();
	}
}
