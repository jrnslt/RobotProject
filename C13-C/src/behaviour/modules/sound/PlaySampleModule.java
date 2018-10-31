package behaviour.modules.sound;

import java.io.File;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class PlaySampleModule extends BehaviourModule {
	private String fileName;
	
	public PlaySampleModule(Marvin marvin, String fileName) {
		super(marvin);
		this.fileName = fileName;
	}

	@Override
	public boolean execute() {
		File testbestand = new File(fileName); //bestand met deze naam is geupoad in het EV3 programma
		Sound.playSample(testbestand, Sound.VOL_MAX); //Speelt een bestand af op max volume
		Delay.msDelay(1000);	
	
		return true;
	}
}
