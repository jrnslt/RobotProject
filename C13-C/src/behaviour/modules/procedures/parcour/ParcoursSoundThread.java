package behaviour.modules.procedures.parcour;

import lejos.hardware.Sound;

public class ParcoursSoundThread extends Thread {

	public ParcoursSoundThread() {
		super();
	}
	
	@Override
	public void run() {
		int count = 0;
		try {
			while (count < 100) {
				Sound.beep();
				sleep(1000);
				count++;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
