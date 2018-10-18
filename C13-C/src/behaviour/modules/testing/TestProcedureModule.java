package behaviour.modules.testing;

import behaviour.modules.GroupModule;
import nl.hva.miw.robot.cohort13.Marvin;


/**
 * @author daniel
 * 
 * Is hetzelfde als de group module
 * Child modules worden toegevoegd in de constructor
 *
 */
public class TestProcedureModule extends GroupModule {

	public TestProcedureModule(Marvin marvin) {
		super(marvin);
		
		final String colorSensorName = "Color Sensor";
		final String colorRecognitionName = "Color recognition";
		final String drivingName = "Driving";
		final String proximitySensorName = "Proximity Sensor";
		final String soundName = "Sound";
		final String touchSensorName = "Touch Sensor";
		final String proximityAndSoundTesterModule = "proximity and sound";
		
		/*
		addModule(new GroupModule(marvin).
				addModule(new StartTestMessageModule(marvin, proximitySensorName)).
				addModule(new ProximitySensorTesterModule(marvin, proximitySensorName)).
				addModule(new EndTestMessageModule(marvin, proximitySensorName))
		);	
		*/
		addModule(new GroupModule(marvin).
				addModule(new StartTestMessageModule(marvin, proximityAndSoundTesterModule)).
				addModule(new ProximityAndSoundTesterModule(marvin, proximityAndSoundTesterModule)).
				addModule(new EndTestMessageModule(marvin, proximityAndSoundTesterModule))
		);
		
		/*
		addModule(new GroupModule(marvin).
				addModule(new StartTestMessageModule(marvin, colorSensorName)).
				addModule(new ColorSensorTesterModule(marvin, colorSensorName)).
				addModule(new EndTestMessageModule(marvin, colorSensorName))
		);
		

		addModule(new GroupModule(marvin).
				addModule(new StartTestMessageModule(marvin, drivingName)).
				addModule(new DriveForwardTesterModule(marvin, drivingName)).
				addModule(new EndTestMessageModule(marvin, drivingName))
		);
		addModule(new GroupModule(marvin).
				addModule(new StartTestMessageModule(marvin, proximitySensorName)).
				addModule(new ProximitySensorTesterModule(marvin, proximitySensorName)).
				addModule(new EndTestMessageModule(marvin, proximitySensorName))
		);
		addModule(new GroupModule(marvin).
				addModule(new StartTestMessageModule(marvin, soundName)).
				addModule(new SoundTesterModule(marvin, soundName)).
				addModule(new EndTestMessageModule(marvin, soundName))
		);	
		addModule(new GroupModule(marvin).
				addModule(new StartTestMessageModule(marvin, touchSensorName)).
				addModule(new TouchSensorTesterModule(marvin, touchSensorName)).
				addModule(new EndTestMessageModule(marvin, touchSensorName))
		);	
		*/
	}
}
