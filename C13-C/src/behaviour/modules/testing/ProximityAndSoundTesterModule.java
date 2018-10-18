package behaviour.modules.testing;

import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import nl.hva.miw.robot.cohort13.Executable;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.ProximitySensor;
import nl.hva.miw.robot.cohort13.SoundProducer;

public class ProximityAndSoundTesterModule extends TestModule {

	public ProximityAndSoundTesterModule(Marvin marvin, String testModuleName) {
		super(marvin, testModuleName);
	}

	@Override
	public boolean execute() {
		SoundProducer soundProducer = new SoundProducer();
		ProximitySensor proximitySensor = new ProximitySensor(getMarvin()); 
		final TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		
		while(lastTime - startTime < 10000) {	        
	        lastTime = System.currentTimeMillis();
	        
			float[] samples = proximitySensor.getSample();
			
			if (samples != null) {
				final int distance = (int)samples[0];
			    textLCD.refresh();
		        textLCD.clear();
		        textLCD.drawString("Distance:", 2, 1);
		        textLCD.drawString("" + distance, 1, 2);
		        
				if (distance < 20000) {
					soundProducer.setExecutable(new Executable() {
						float x = 0;
						
						@Override
						public void execute() {
							// TODO Auto-generated method stub
						    textLCD.refresh();
					        textLCD.clear();
					        textLCD.drawString("Close:", 2, 1);
					        textLCD.drawString("" + distance, 1, 2);

							int frequency = (int)(Math.sin(x) * 50);
							Sound.playTone(frequency, 1, 50);
							x += 0.01f;
						}
						
					});
					
				}
			}
		}
		
		return true;
	}

}
