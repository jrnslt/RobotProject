package parcours;

import behaviour.modules.BehaviourModule;
import behaviour.modules.testing.DriveForwardTesterModule;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.ZwartEnWit;

public class ParcoursModule extends BehaviourModule{

	public ParcoursModule(Marvin marvin) {
		super(marvin);
	}

	
	
	@Override
	public boolean execute() {
		ZwartEnWit testerZW = new ZwartEnWit();
		DriveForwardTesterModule testrij = new DriveForwardTesterModule(marvin, "Rijen");
		
		int gedrag = testerZW.test(marvin);
		
		
		while (gedrag != 0) {
			
			
			
			if (gedrag == 3) {
				testrij.testDriveForward();
			} else if (gedrag == 2) {
				testrij.goLeft();
			}
			else if (gedrag == 1) {
				testrij.goRight();
			}
			
			else {
				testrij.driveFast();
			}
		
		}
		

		
		
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
