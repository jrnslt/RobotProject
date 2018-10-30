package behaviour.modules.procedures.keuze_opdracht;

import java.io.File;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.PlayLoopedSound;
import nl.hva.miw.robot.cohort13.resources.Sounds;

public class VictoryDanceOpdracht2Module extends BehaviourModule {

	public VictoryDanceOpdracht2Module(Marvin marvin) {
		super(marvin);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * do victory dance
	 * 
	 * 
	 */
	@Override
	public boolean execute() {
		File testbestand = new File(Sounds.woopwoop); //bestand met deze naam is geupoad in het EV3 programma
		Sound.playSample(testbestand, Sound.VOL_MAX); //Speelt een bestand af op max volume
		Delay.msDelay(1000);	
		
		PlayLoopedSound p = new PlayLoopedSound(10);
		p.start();
		
		getMarvin().getMotorControl().stop();
    	getMarvin().getMotorControl().rotate360Forward();
    	getMarvin().getMotorControl().stop();
    	getMarvin().getMotorControl().rotate360Backward();
    	getMarvin().getMotorControl().stop();
    	getMarvin().getMotorControl().rotate360Forward();
    	getMarvin().getMotorControl().stop();
    	
    	for (int i = 0; i < 5; i ++) {
	    	getMarvin().getMotorControl().grabItForward(500,500);
	    	getMarvin().getMotorControl().stop();
	    	getMarvin().getMotorControl().letLoose(500, 500);
	      	getMarvin().getMotorControl().stop();
    	}
    	
		try {
			p.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
}
