package behaviour.modules;

import nl.hva.miw.robot.cohort13.Marvin;

/**
 * 
 * behaviour modules voeren taken uit.
 * Deze taken zijn gedefinieerd in @method execute()
 *
 */
public abstract class BehaviourModule {
	private Marvin marvin;
	
	public BehaviourModule(Marvin marvin) {
		this.marvin = marvin;
	}
	
	public Marvin getMarvin() {
		return marvin;
	}
	
	public abstract boolean execute();
}
