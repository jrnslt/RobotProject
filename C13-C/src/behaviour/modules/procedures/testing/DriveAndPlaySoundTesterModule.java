package behaviour.modules.procedures.testing;

import behaviour.modules.BehaviourModule;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class DriveAndPlaySoundTesterModule extends BehaviourModule {

	public DriveAndPlaySoundTesterModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		getMarvin().getMotorControl().drive(30, 30);	//Drive Slow
		Delay.msDelay(4000);
		getMarvin().getMotorControl().drive(800, 800);	//Drive Fast
		Delay.msDelay(1000);
		getMarvin().getMotorControl().stop();	//Stop
		
		return true;
	}
}


	
