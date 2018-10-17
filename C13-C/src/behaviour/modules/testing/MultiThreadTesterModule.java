package behaviour.modules.testing;

import nl.hva.miw.robot.cohort13.Marvin;

public class MultiThreadTesterModule extends TestModule {
	
	public MultiThreadTesterModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public void execute() {
		Runnable runAble = (new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		
		Thread t = new Thread();
	}
}
