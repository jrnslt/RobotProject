package behaviour.modules;

import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.MarvinComponent;

/**
 * 
 * behaviour modules voeren taken uit.
 * Deze taken zijn gedefinieerd in @method execute()
 *
 */
public abstract class BehaviourModule extends MarvinComponent {
	
	public BehaviourModule(Marvin marvin) {
		super(marvin);
	}
	
	public abstract boolean execute();
}
