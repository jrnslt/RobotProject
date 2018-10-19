package behaviour.modules.logic;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;

/**
 * loops submodule as long as it returns true
 * returns true
 */
public class LoopModule extends BehaviourModule {
	private BehaviourModule subModule;
	
	public LoopModule(Marvin marvin) {
		super(marvin);
	}
	
	public LoopModule addModule(BehaviourModule subModule) {
		this.subModule = subModule;
		return this;
	}

	@Override
	public boolean execute() {
		boolean run = true;
		
		while (run) {
			run = subModule.execute();
		}
		
		return true;
	}

}
