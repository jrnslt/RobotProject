package behaviour.modules.procedures.keuze_opdracht;

import java.io.File;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;
<<<<<<< HEAD
import nl.hva.miw.robot.cohort13.PlayLoopedSound;
=======

import nl.hva.miw.robot.cohort13.PlayWoopWoop;
>>>>>>> cf9b2785cf7f3c35e712d27e4ee08d1812083733
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
		
		PlayLoopedSound p = new PlayLoopedSound(10);
		p.start();
		
//		PlayWoopWoop p = new PlayWoopWoop(10);
//		p.start();


		getMarvin().getMotorControl().stop();
    	getMarvin().getMotorControl().rotate360Forward();
  //  	getMarvin().getMotorControl().stop();
    	for (int i = 0; i < 5; i ++) {
	    	getMarvin().getMotorControl().grabItForward(500,500);
	    	getMarvin().getMotorControl().stop();
	    	getMarvin().getMotorControl().letLoose(500, 500);
	      	getMarvin().getMotorControl().stop();
      	
    	}
    	getMarvin().getMotorControl().rotate360Backward();
    	getMarvin().getMotorControl().stop();
    	getMarvin().getMotorControl().rotate360Forward();
    	
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


//    	try {
//    		p.join();
//    	} catch (InterruptedException e) {
//    		e.printStackTrace();
//    	}
    	
    	getMarvin().getMotorControl().stop();

		return true;
	}
}
