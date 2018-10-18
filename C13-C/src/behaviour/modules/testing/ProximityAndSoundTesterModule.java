package behaviour.modules.testing;

import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.ProximitySensor;
import nl.hva.miw.robot.cohort13.SoundProducer;

public class ProximityAndSoundTesterModule extends TestModule {

	public ProximityAndSoundTesterModule(Marvin marvin, String testModuleName) {
		super(marvin, testModuleName);
	}

	@Override
	public boolean execute() {
		SoundProducer soundProducer = new SoundProducer();
		ProximitySensor proximitySensor = new ProximitySensor(getMarvin()); 
		
		return true;
	}

}
