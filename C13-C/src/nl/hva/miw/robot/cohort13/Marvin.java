package nl.hva.miw.robot.cohort13;

import behaviour.modules.GroupModule;
import behaviour.modules.WelcomeModule;
import behaviour.modules.testing.TestProcedureModule;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.utility.Delay;
import parcours.ParcoursModule;

public class Marvin  implements HardwareInterface  {
	private Brick brick;
	private GroupModule mainModule;

	public Marvin() {
		super(); 
		brick = LocalEV3.get();
		mainModule = new GroupModule(this);

		mainModule.addModule(new WelcomeModule(this));
		//mainModule.addModule(new ParcoursModule(this));
		
		mainModule.addModule(new TestProcedureModule(this));
	}
	
	public Brick getBrick() {
		return brick;
	}
	
	public static void main(String[] args) {
		Marvin marvin = new Marvin();
		marvin.run();
	}
	
	private void run() {
		mainModule.execute();		
		waitForKey(Button.ENTER);
	}
	
	public void waitForKey(Key key) {
		while(key.isUp()) {
			Delay.msDelay(100);
		}
		while(key.isDown()) {
			Delay.msDelay(100);
		}
	}
}
