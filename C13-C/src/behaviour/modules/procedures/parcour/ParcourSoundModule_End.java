package behaviour.modules.procedures.parcour;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.sound.PlayLoopedSound;

public class ParcourSoundModule_End extends BehaviourModule {
	private PlayLoopedSound p;
	
	public ParcourSoundModule_End(Marvin marvin, PlayLoopedSound p) {
		super(marvin);
		this.p = p;
	}

	@Override
	public boolean execute() {
		try {
			p.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return true;
	}
}
