package nl.hva.miw.robot.cohort13;

import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class ProximitySensor {
	private float [] sample;
	
	public ProximitySensor(Marvin marvin) {
		super();
		
		final EV3IRSensor proximitySensor = marvin.proximitySensor;
		
		Runnable runnable = (new Runnable() {

			@Override
			public void run() {
				
				while(true) {
					final SampleProvider sp = proximitySensor.getDistanceMode();	
					sample = new float[sp.sampleSize()];
				    sp.fetchSample(sample, 0);	
				}	
			}
		});
		
		Thread thread = new Thread(runnable);
		thread.start();
	}
	
	public float[] getSample() {
		return sample;
	}
}

