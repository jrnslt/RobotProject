package behaviour.modules.sound;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class SystemSoundModule extends BehaviourModule {
	private int code;
	
	public SystemSoundModule(Marvin marvin, int code) {
		super(marvin);
		this.code = code;
	}

	@Override
	public boolean execute() {
		Sound.systemSound(false, code);
		Delay.msDelay(1000);
		return true;
	}

}
