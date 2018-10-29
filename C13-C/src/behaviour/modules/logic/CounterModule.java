package behaviour.modules.logic;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;

/**
 * counter return true if max count is reached
 * else returns false
 */
public class CounterModule extends BehaviourModule {
	private final int maxCount;
	private int count;
	
	public CounterModule(Marvin marvin, int maxCount) {
		super(marvin);
		this.maxCount = maxCount;
		this.count = 0;
	}

	@Override
	public boolean execute() {
		if (count < maxCount) {
			count++;
			return false;
		}
		return true;
	}
}
