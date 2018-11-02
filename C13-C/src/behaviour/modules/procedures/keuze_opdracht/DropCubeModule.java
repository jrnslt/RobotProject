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
	 * draai om en drop de kubus
	 */
	@Override
	public boolean execute() {

		Delay.msDelay(500);
		getMarvin().getMotorControl().stop();
     	getMarvin().getMotorControl().rotateClockwise(200, 3000);
     	getMarvin().getMotorControl().stop();
     	Delay.msDelay(500);
     	getMarvin().getMotorControl().driveBackwards(200, 200, 250);
     	Delay.msDelay(500);
     	getMarvin().getMotorControl().stop();
    	getMarvin().getMotorControl().letLoose(150, 2300);
    	getMarvin().getMotorControl().stop();
    	Delay.msDelay(500);
		
		return true;
	}
}
