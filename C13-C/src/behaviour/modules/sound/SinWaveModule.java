package behaviour.modules.sound;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import nl.hva.miw.robot.cohort13.Marvin;

public class SinWaveModule extends BehaviourModule {

	public SinWaveModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		float x = 0;
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		
		while (lastTime - startTime < 10000) {
			lastTime = System.currentTimeMillis(); 
			int frequency = (int)(Math.sin(x) * 100);
			Sound.playTone(frequency, 1, 50);
			x += 0.1f;
		}
		
		return true;
	}

}
