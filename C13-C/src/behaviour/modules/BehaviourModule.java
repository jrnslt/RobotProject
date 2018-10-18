package behaviour.modules;

import nl.hva.miw.robot.cohort13.Marvin;

/**
 * @author daniel
 * 
 * behaviour modules voeren taken uit.
 * Deze taken zijn gedefinieerd in @method execute()
 *
 */
public abstract class BehaviourModule {
	protected Marvin marvin;
	
	public BehaviourModule(Marvin marvin) {
		this.marvin = marvin;
	}
	
	public Marvin getMarvin() {
		return marvin;
	}
	
	/**
	 * @return geeft boolean terug waar e.v.t iets mee gedaan kan worden vanuit andere modules
	 */
	public abstract boolean execute();
}
