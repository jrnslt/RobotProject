package behaviour.modules.logic;

import java.util.ArrayList;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;

/**
 * 
 * executes sub modules in sequence until a submodule returns false
 * return true if all child modules return true
 * return false if any child module returns false
 */
public class SequenceUntilFailModule extends BehaviourModule {
	private final ArrayList<BehaviourModule> modules;
	
	public SequenceUntilFailModule(Marvin marvin) {
		super(marvin);
		this.modules = new ArrayList<>();
	}
	
	public SequenceUntilFailModule addModule(BehaviourModule module) {
		this.modules.add(module);
		return this;
	}
	
	@Override
	/*
	 * returns true: if all submodules are executed and all have returned true;
	 * return false: as soon as a submodule returns false
	 */
	public boolean execute() {
		for (BehaviourModule module : modules) {
			boolean result = module.execute();
			
			if (!result) {
				return false;
			}
		}
		
		return true;
	}
}
