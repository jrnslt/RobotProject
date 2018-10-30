package nl.hva.miw.robot.cohort13.functionality;

import nl.hva.miw.robot.cohort13.Marvin;

public class MemoryOpdracht2 extends MarvinComponent {

	public boolean redCubeFound;
	public boolean redCubeDelivered;
	public boolean greenCubeFound;
	public boolean greenCubeDelivered;	
	public boolean blueCubeFound;
	public boolean blueCubeDelivered;
	
	public MemoryOpdracht2(Marvin marvin) {
		super(marvin);
		// TODO Auto-generated constructor stub
	}

	public void reset() {
		redCubeFound = false;
		redCubeDelivered = false;
		greenCubeFound = false;
		greenCubeDelivered = false;
		blueCubeFound = false;
		blueCubeDelivered = false;
	}
}
