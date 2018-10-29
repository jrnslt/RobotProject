package behaviour.modules.procedures.parcour;

import behaviour.modules.BehaviourModule;
import behaviour.modules.DelayModule;
import behaviour.modules.logic.SequenceModule;
import behaviour.modules.sound.BeepModule;
import behaviour.modules.sound.PlaySampleModule;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.PlayLoopedSound;

public class ParcourSoundModule_Start extends BehaviourModule {
	private PlayLoopedSound p;
	
	public ParcourSoundModule_Start(Marvin marvin, PlayLoopedSound p) {
		super(marvin);
		this.p = p;
	}

	@Override
	public boolean execute() {
		p.run();
			
		return true;
	}
}
