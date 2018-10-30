package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;

public class SetVariableBlueCubeFoundModule extends BehaviourModule {
	private boolean value;
	
	public SetVariableBlueCubeFoundModule(Marvin marvin, boolean value) {
		super(marvin);
		this.value = value;
	}

	@Override
	public boolean execute() {
		getMarvin().getMemoryOpdracht2().blueCubeFound = value;
		return true;
	}
}
