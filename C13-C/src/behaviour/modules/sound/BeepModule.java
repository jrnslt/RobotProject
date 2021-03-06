package behaviour.modules.sound;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class BeepModule extends BehaviourModule {

	public BeepModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		Sound.beep();
		return true;
	}

}
