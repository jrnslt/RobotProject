package behaviour.modules.procedures.testing;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.Utils;
import nl.hva.miw.robot.cohort13.functionality.ProximityControl;

public class ProximityAndSoundTesterModule extends BehaviourModule {

	public ProximityAndSoundTesterModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		//ProximitySensor proximitySensor = getMarvin().proximitySensor;
		final TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		int count = 0;
		
		while(lastTime - startTime < 10000) {	        
	        lastTime = System.currentTimeMillis();
	       
		    //textLCD.refresh();
	        //textLCD.clear();
	        //textLCD.drawString("Distance:", 2, 1);
	        //textLCD.drawString("" + distance, 1, 2);

	        if (count % 16 == 0) {
				float x = 0;
				ProximityControl proximitySensor = getMarvin().getProximityControl();

				int distance = proximitySensor.getDistance();
				double sigmoid = Utils.sigmoid(distance / 200f, 5f);
				
				// TODO Auto-generated method stub
			    textLCD.refresh();
		        textLCD.clear();
		        textLCD.drawString("distance:", 2, 1);
		        textLCD.drawString("" + distance, 1, 2);
		        textLCD.drawString("" + sigmoid, 1, 3);

		        double invertedSigmoid = 1.0 - sigmoid;
		     
				Sound.playTone(50, 1, (int)(invertedSigmoid * 100));		
	        }
	        
	        count ++;
		}
		
		return true;
	}

}
