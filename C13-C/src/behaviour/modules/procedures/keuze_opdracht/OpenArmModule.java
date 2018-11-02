package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class OpenArmModule extends BehaviourModule {

	public OpenArmModule(Marvin marvin) {
		super(marvin);

	}

	@Override
	public boolean execute() {
     	Delay.msDelay(500);
     	getMarvin().getMotorControl().stop();
    	getMarvin().getMotorControl().letLoose(150, 2300);
    	getMarvin().getMotorControl().stop();
    	Delay.msDelay(500);
    	
		return true;
	}

}
