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
		getMarvin().getMotorControl().stop();
        getMarvin().getMotorControl().rotate180DegreesClockwise();
        getMarvin().getMotorControl().stop();
        Delay.msDelay(500);
        getMarvin().getMotorControl().driveBackwards(150, 150, 500);
        Delay.msDelay(500);
        getMarvin().getMotorControl().stop();
        getMarvin().getMotorControl().grabItForward(150, 2900);
        getMarvin().getMotorControl().stop();
        Delay.msDelay(500);
        
		return true;
	}

}
