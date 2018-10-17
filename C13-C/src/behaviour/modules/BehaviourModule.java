package behaviour.modules;

import behaviour.BehaviourAble;
import nl.hva.miw.robot.cohort13.Marvin;

public abstract class BehaviourModule implements BehaviourAble {
	protected Marvin marvin;
	
	public BehaviourModule(Marvin marvin) {
		this.marvin = marvin;
	}
	
	public Marvin getMarvin() {
		return marvin;
	}
}
