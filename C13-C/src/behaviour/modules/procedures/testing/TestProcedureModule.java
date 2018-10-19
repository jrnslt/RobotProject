package behaviour.modules.procedures.testing;

import behaviour.modules.DelayModule;
import behaviour.modules.WaitForKeyModule;
import behaviour.modules.logic.SequenceModule;
import behaviour.modules.sound.BeepModule;
import behaviour.modules.sound.BeepSequenceModule;
import behaviour.modules.sound.BeepSequenceUpModule;
import behaviour.modules.sound.BuzzModule;
import behaviour.modules.sound.PlaySampleModule;
import behaviour.modules.sound.SinWaveModule;
import behaviour.modules.sound.SystemSoundModule;
import nl.hva.miw.robot.cohort13.Marvin;


/**
 * @author daniel
 * 
 * Is hetzelfde als de group module
 * Child modules worden toegevoegd in de constructor
 *
 */
public class TestProcedureModule extends SequenceModule {

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
		

		//Console Module
		addModule(new SequenceModule(marvin).
				addModule(new StartTestMessageModule(marvin, consoleName)).
				addModule(new WaitForKeyModule(marvin)).
				addModule(new EndTestMessageModule(marvin, consoleName))
		).
		
		//Proximity
		addModule(new SequenceModule(marvin).
				addModule(new StartTestMessageModule(marvin, proximityAndSoundTesterModule)).
				//addModule(new ProximityAndSoundTesterModule(marvin, proximityAndSoundTesterModule)).
				addModule(new PlaySampleModule(marvin, "woopwoop2.wav")).
				addModule(new DelayModule(marvin, 1000)).
				addModule(new PlaySampleModule(marvin, "woopwoop3.wav")).
				addModule(new DelayModule(marvin, 1000)).
				addModule(new PlaySampleModule(marvin, "We_are_the_robotsSHORT.wav")).
				
				addModule(new EndTestMessageModule(marvin, proximityAndSoundTesterModule))
		).
		//Sound
		addModule(new SequenceModule(marvin).
				addModule(new StartTestMessageModule(marvin, soundName)).
				//addModule(new SoundTesterModule(marvin, soundName)).
				//addModule(new PlaySampleModule(marvin, "woopwoop.wav"))
				//addModule(new SinWaveModule(marvin)).
				//addModule(new BeepModule(marvin)).
				//addModule(new BeepSequenceModule(marvin)).
				//addModule(new BeepSequenceUpModule(marvin)).
				//addModule(new BuzzModule(marvin)).
				//addModule(new SystemSoundModule(marvin, 0)).
				//addModule(new SystemSoundModule(marvin, 1)).
				//addModule(new SystemSoundModule(marvin, 2)).
				//addModule(new SystemSoundModule(marvin, 3)).
				//addModule(new SystemSoundModule(marvin, 4)).
				addModule(new EndTestMessageModule(marvin, soundName))
		);	
		
	*/
		addModule(new SequenceModule(marvin).
				addModule(new StartTestMessageModule(marvin, colorSensorName)).
				addModule(new ColorSensorTesterModule(marvin, colorSensorName)).
				addModule(new EndTestMessageModule(marvin, colorSensorName))
		);
		/*

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
