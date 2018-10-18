package behaviour.modules;

import nl.hva.miw.robot.cohort13.Marvin;

public abstract class BehaviourModule {
	protected Marvin marvin;
	
	public BehaviourModule(Marvin marvin) {
		this.marvin = marvin;
	}
	
	public Marvin getMarvin() {
		return marvin;
	}
	
	public abstract boolean execute();
}
