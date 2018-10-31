package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class GrabCubeModule extends BehaviourModule {

	public GrabCubeModule(Marvin marvin) {
		super(marvin);
	}

	/**
	 * draai om en grijp cubus
	 */
	@Override
	public boolean execute() {
	
        	getMarvin().getMotorControl().stop();
        	getMarvin().getMotorControl().rotate180();
        	getMarvin().getMotorControl().stop();
        	getMarvin().getMotorControl().driveBackwards(300, 300, 150); 
        	getMarvin().getMotorControl().stop();
        	getMarvin().getMotorControl().grabItForward(200,1700);
        	getMarvin().getMotorControl().stop();
		
		return true;
	}

}
