package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.resources.Sounds;
import nl.hva.miw.robot.cohort13.sound.PlaySound;

/**
 	this classe is for the vicotory dance where different methods are used en where multithreading is used for playing a song.
 */
public class VictoryDanceOpdracht2Module extends BehaviourModule {

	public VictoryDanceOpdracht2Module(Marvin marvin) {
		super(marvin);
		// TODO Auto-generated constructor stub
	}

	/**
	 * do victory dance
	 */
	@Override
	public boolean execute() {	
		PlaySound p = new PlaySound(Sounds.woopwoop, 10);
		p.start();
		
		getMarvin().getMotorControl().stop();
    	getMarvin().getMotorControl().rotate360DegreesClockwise();
    	
    	for (int i = 0; i < 5; i ++) {
	    	getMarvin().getMotorControl().grabItForward(500,700);
	    	getMarvin().getMotorControl().stop();
	    	getMarvin().getMotorControl().letLoose(500,700);
	      	getMarvin().getMotorControl().stop();
    	}
    	
    	getMarvin().getMotorControl().rotate360DegreesCounterClockwise();
    	getMarvin().getMotorControl().stop();
    	
    	try {
    		p.join();
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    	
    	getMarvin().getMotorControl().stop();
		return true;
	}
}
