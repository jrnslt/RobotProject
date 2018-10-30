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
	 * 
	 * draai om en grijp cubus
	 * 
	 * 
	 */
	@Override
	public boolean execute() {
	
        	getMarvin().getMotorControl().stop();
        	getMarvin().getMotorControl().driveBackwards(300, 300, 100); 
        	getMarvin().getMotorControl().stop();
        	getMarvin().getMotorControl().rotate180();
        	getMarvin().getMotorControl().stop();
        	getMarvin().getMotorControl().grabItForward(300,1900);
        	getMarvin().getMotorControl().stop();
        	
        	Delay.msDelay(300);
         	getMarvin().getMotorControl().rotate180();
        	getMarvin().getMotorControl().stop();
        	getMarvin().getMotorControl().letLoose(300, 1200);
		
		return true;
	}

}
