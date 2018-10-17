package behaviour.modules.testing;

import behaviour.modules.GroupModule;
import nl.hva.miw.robot.cohort13.Marvin;

public class TestProcedureModule extends GroupModule {

	public TestProcedureModule(Marvin marvin) {
		super(marvin);
		addModule(new ColorSensorTesterModule(marvin));
		addModule(new ColorSensorTesterV2Module(marvin));
		addModule(new DriveForwardTesterModule(marvin));
		addModule(new ProximitySensorTesterModule(marvin));
		addModule(new SoundTesterModule(marvin));
	}
}
