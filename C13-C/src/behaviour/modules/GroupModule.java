package behaviour.modules;

import java.util.ArrayList;

import nl.hva.miw.robot.cohort13.Marvin;

public class GroupModule extends BehaviourModule {
	private final ArrayList<BehaviourModule> modules;
	
	public GroupModule(Marvin marvin) {
		super(marvin);
		this.modules = new ArrayList<>();
	}
	
	public GroupModule addModule(BehaviourModule module) {
		this.modules.add(module);
		return this;
	}
	
	@Override
	public boolean execute() {
		for (BehaviourModule module : modules) {
			module.execute();
		}
		
		return true;
	}
}
