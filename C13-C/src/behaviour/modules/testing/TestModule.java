package behaviour.modules.testing;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import nl.hva.miw.robot.cohort13.Marvin;

public abstract class TestModule extends BehaviourModule  {
	private String testModuleName;
	
	public TestModule(Marvin marvin, String testModuleName) {
		super(marvin);
		this.testModuleName = testModuleName;
	}
}
