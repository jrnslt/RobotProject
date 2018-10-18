package behaviour.modules;

import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class DelayModule extends BehaviourModule {
	private int delayMS;
	
	public DelayModule(Marvin marvin, int delayMS) {
		super(marvin);
		this.delayMS = delayMS;
	}

	@Override
	public boolean execute() {
		Delay.msDelay(delayMS);
		return true;
	}
}
