package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;

public class SetVariableRedCubeFoundModule extends BehaviourModule {
	private boolean value;
	
	public SetVariableRedCubeFoundModule(Marvin marvin, boolean value) {
		super(marvin);
		this.value = value;
	}

	@Override
	public boolean execute() {
		getMarvin().getMemoryOpdracht2().redCubeFound = value;
		return true;
	}
}
