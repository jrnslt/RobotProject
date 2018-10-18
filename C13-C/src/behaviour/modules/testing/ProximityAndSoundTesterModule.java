package behaviour.modules.testing;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import nl.hva.miw.robot.cohort13.Executable;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.ProximitySensor;
import nl.hva.miw.robot.cohort13.SoundProducer;
import nl.hva.miw.robot.cohort13.Utils;

public class ProximityAndSoundTesterModule extends BehaviourModule {

	public ProximityAndSoundTesterModule(Marvin marvin, String testModuleName) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		SoundProducer soundProducer = new SoundProducer();
		ProximitySensor proximitySensor = new ProximitySensor(getMarvin()); 
		final TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		int count = 0;
		
		while(lastTime - startTime < 10000) {	        
	        lastTime = System.currentTimeMillis();
	        
			float[] samples = proximitySensor.getSample();
			
			if (samples != null) {
				
				final int distance = (int)samples[0];
				/*
			    textLCD.refresh();
		        textLCD.clear();
		        textLCD.drawString("Distance:", 2, 1);
		        textLCD.drawString("" + distance, 1, 2);
		        */

		        if (count % 16 == 0) {
					soundProducer.setExecutable(new Executable() {
						float x = 0;
						
						@Override
						public void execute() {
							double sigmoid = Utils.sigmoid(distance * 10, 100f);
							
							// TODO Auto-generated method stub
						    textLCD.refresh();
					        textLCD.clear();
					        textLCD.drawString("distance:", 2, 1);
					        textLCD.drawString("" + distance, 1, 2);
					        textLCD.drawString("" + sigmoid, 1, 3);
	
					        double invertedSigmoid = 1.0 - sigmoid;
					     
							Sound.playTone(50, 1, (int)(invertedSigmoid * 100));
						}
					});
		        }
		        
		        count ++;
			}
		}
		
		return true;
	}

}
