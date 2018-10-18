package behaviour.modules.sound;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class BeepSequenceUpModule extends BehaviourModule {

	public BeepSequenceUpModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		Sound.beepSequenceUp();
		Delay.msDelay(1000);
		return true;
	}
}
