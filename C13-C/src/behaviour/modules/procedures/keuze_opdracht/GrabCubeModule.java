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
			Delay.msDelay(500);
        	getMarvin().getMotorControl().driveBackwards(200, 200);
			Delay.msDelay(1000);
//			getMarvin().getMotorControl().driveForward(-300, 300);
//			Delay.msDelay(2000);
        	getMarvin().getMotorControl().rotate180_2();
        	getMarvin().getMotorControl().stop();
			Delay.msDelay(500);
        	getMarvin().getMotorControl().driveBackwards(200, 200); 
			Delay.msDelay(1000);
        	getMarvin().getMotorControl().stop();
			Delay.msDelay(500);
        	getMarvin().getMotorControl().grabItForward(200,1700);
        	getMarvin().getMotorControl().stop();
		
		return true;
	}

}
