package behaviour.modules.procedures.testing;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;

public class MultiThreadTesterModule extends BehaviourModule {
	
	public MultiThreadTesterModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		Runnable runAble = (new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		
		Thread t = new Thread();
		
		return true;
	}
}
