package behaviour.modules.testing;

import behaviour.modules.GroupModule;
import behaviour.modules.testing.sound.BeepModule;
import behaviour.modules.testing.sound.BeepSequenceModule;
import behaviour.modules.testing.sound.BeepSequenceUpModule;
import behaviour.modules.testing.sound.BuzzModule;
import behaviour.modules.testing.sound.PlaySampleModule;
import behaviour.modules.testing.sound.SinWaveModule;
import behaviour.modules.testing.sound.SoundTesterModule;
import behaviour.modules.testing.sound.SystemSoundModule;
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
		
		final String consoleName = "Console";
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

		//Console Module
		addModule(new GroupModule(marvin).
				addModule(new StartTestMessageModule(marvin, consoleName)).
				addModule(new ConsoleTesterModule(marvin)).
				addModule(new EndTestMessageModule(marvin, consoleName))
		).
		
		//Proximity
		addModule(new GroupModule(marvin).
				addModule(new StartTestMessageModule(marvin, proximityAndSoundTesterModule)).
				addModule(new ProximityAndSoundTesterModule(marvin, proximityAndSoundTesterModule)).
				addModule(new EndTestMessageModule(marvin, proximityAndSoundTesterModule))
		).
		//Sound
		addModule(new GroupModule(marvin).
				addModule(new StartTestMessageModule(marvin, soundName)).
				addModule(new SoundTesterModule(marvin, soundName))
				//addModule(new PlaySampleModule(marvin))
				/*
				addModule(new SinWaveModule(marvin)).
				addModule(new BeepModule(marvin)).
				addModule(new BeepSequenceModule(marvin)).
				addModule(new BeepSequenceUpModule(marvin)).
				addModule(new BuzzModule(marvin)).
				addModule(new SystemSoundModule(marvin, 0)).
				addModule(new SystemSoundModule(marvin, 1)).
				addModule(new SystemSoundModule(marvin, 2)).
				addModule(new SystemSoundModule(marvin, 3)).
				addModule(new SystemSoundModule(marvin, 4)).
				addModule(new EndTestMessageModule(marvin, soundName))
				*/
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
				addModule(new StartTestMessageModule(marvin, touchSensorName)).
				addModule(new TouchSensorTesterModule(marvin, touchSensorName)).
				addModule(new EndTestMessageModule(marvin, touchSensorName))
		);	
		*/
	}
}
