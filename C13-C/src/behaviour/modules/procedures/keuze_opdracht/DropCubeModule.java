package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class DropCubeModule extends BehaviourModule {

	public DropCubeModule(Marvin marvin) {
		super(marvin);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * draai om en drop de kubus
	 * 
	 * 
	 */
	@Override
	public boolean execute() {

		getMarvin().getMotorControl().stop();
     	getMarvin().getMotorControl().rotate180();
     	getMarvin().getMotorControl().stop();
    	getMarvin().getMotorControl().letLoose(300, 1500);
    	getMarvin().getMotorControl().stop();
		
		return true;
	}
}
