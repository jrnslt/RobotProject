package behaviour.modules.procedures.testing;

import behaviour.modules.BehaviourModule;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

/**
 * @author daniel
 * 
 * Test module voor testen van de proximity sensor
 *
 */
public class ProximitySensorTesterModule extends BehaviourModule {

	public ProximitySensorTesterModule(Marvin marvin, String testModuleName) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		final int HALF_SECOND = 500;
	
		long startTime = System.currentTimeMillis();
		long currentTime = System.currentTimeMillis();
		
		while(currentTime - startTime < 10000) {
			currentTime = System.currentTimeMillis();

		    int distanceValue = getMarvin().getProximityManager().getDistance();
		    
		    textLCD.refresh();
	        textLCD.clear();
	        textLCD.drawString("Distance:", 2, 1);
	        textLCD.drawString("" + distanceValue, 1, 2);
	        Delay.msDelay(HALF_SECOND);	
		}
		
		return true;
	}
}


