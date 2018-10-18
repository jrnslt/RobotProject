package behaviour.modules.testing.sound;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class BeepSequenceModule extends BehaviourModule {

	public BeepSequenceModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		Sound.beepSequence();
		Delay.msDelay(1000);
		return true;
	}

}
