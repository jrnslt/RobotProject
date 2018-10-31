package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.PlayWoopWoop;

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
		
		PlayWoopWoop p = new PlayWoopWoop(10);
		p.start();
		
		getMarvin().getMotorControl().stop();
    	getMarvin().getMotorControl().rotate360Forward();
   // 	getMarvin().getMotorControl().stop();
    	for (int i = 0; i < 5; i ++) {
	    	getMarvin().getMotorControl().grabItForward(500,700);
	    	getMarvin().getMotorControl().stop();
	    	getMarvin().getMotorControl().letLoose(500,700);
	      	getMarvin().getMotorControl().stop();
    	}
    	getMarvin().getMotorControl().rotate360Backward();
    	getMarvin().getMotorControl().stop();
 //   	getMarvin().getMotorControl().rotate360Forward();
    	
    	try {
   		p.join();
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    	
    	getMarvin().getMotorControl().stop();
		return true;
	}
}
