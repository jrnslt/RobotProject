package behaviour.modules.testing;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class ProximitySensorTesterModule extends TestModule {

	public ProximitySensorTesterModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public void execute() {
		EV3IRSensor afstandTester = new EV3IRSensor(SensorPort.S1);
		final int HALF_SECOND = 500;
		final int iteration_threshold = 50;		// 50milliseconde			
		final SampleProvider sp = afstandTester.getDistanceMode();
		int distanceValue = 0;
		int testMode = 0;
	
			//Control loop
			
		while(testMode == 0) {
			float [] sample = new float[sp.sampleSize()];
		    sp.fetchSample(sample, 0);	
		    distanceValue = (int)sample[0];
		    
			System.out.println("Distance: {}" + distanceValue);
			Delay.msDelay(HALF_SECOND);	
		}
	}
}


