package nl.hva.miw.robot.cohort13;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class ProximitySensor {
	private float [] sample;
	private Thread thread;
	public final EV3IRSensor proximitySensor;
	
	public ProximitySensor(Marvin marvin) {
		super();
		
		proximitySensor = new EV3IRSensor(SensorPort.S1);
		
		Runnable runnable = (new Runnable() {

			@Override
			public void run() {
				final SampleProvider sp = proximitySensor.getDistanceMode();	
				sample = new float[sp.sampleSize()];
				
				while(true) {
				    sp.fetchSample(sample, 0);	
				}	
			}
		});
		
		thread = new Thread(runnable);
		thread.start();
	}
	
	public float[] getSample() {
		return sample;
	}
}

