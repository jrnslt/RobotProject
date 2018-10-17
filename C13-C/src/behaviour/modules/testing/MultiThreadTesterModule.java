package behaviour.modules.testing;

import nl.hva.miw.robot.cohort13.Marvin;

public class MultiThreadTesterModule extends TestModule {
	
	public MultiThreadTesterModule(Marvin marvin, String testModuleName) {
		super(marvin, testModuleName);
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
