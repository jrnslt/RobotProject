package behaviour.modules.procedures.console;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Button;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.MarvinState;
import nl.hva.miw.robot.cohort13.functionality.KeyInputControl;

public class ConsoleModule extends BehaviourModule {

	public ConsoleModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		getMarvin().state = MarvinState.MENU;
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		KeyInputControl keyInputManager = getMarvin().getKeyInputManager();
		
		while (true) {
			if (keyInputManager.getMarvin().keyPressed(Button.LEFT)) {
				getMarvin().incrementState(-1);
			} else if (keyInputManager.getMarvin().keyPressed(Button.RIGHT)) {
				getMarvin().incrementState(1);
			}
			
		    textLCD.refresh();
	        textLCD.clear();
	        textLCD.drawString("Select Mode: ", 0, 1);
	        textLCD.drawString(getMarvin().state.stateName, 0, 2);
	        textLCD.drawString("Press Enter to start", 0, 3);
			
			if (Button.ENTER.isDown()) {
				return true;
			}
		}
	}
}
