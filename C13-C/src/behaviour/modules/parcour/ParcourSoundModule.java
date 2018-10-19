package behaviour.modules.parcour;

import behaviour.modules.BehaviourModule;
import behaviour.modules.DelayModule;
import behaviour.modules.GroupModule;
import behaviour.modules.sound.PlaySampleModule;
import lejos.hardware.Sound;
import nl.hva.miw.robot.cohort13.Marvin;

public class ParcourSoundModule extends BehaviourModule {

	public ParcourSoundModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		Runnable runnable = (new Runnable() {
		int count = 0;
		
			@Override
			public void run() {
				GroupModule groupModule = 
						new GroupModule(getMarvin()).
							addModule(new PlaySampleModule(marvin, "woopwoop2.wav")).
							addModule(new DelayModule(getMarvin(), 500));
				
				while (count < 20) {
					groupModule.execute();
				}
			}
			
		});
		
		Thread thread = new Thread(runnable);
		thread.run();
		
		
		return true;
	}

}
