package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;

public class SetVariableGreenCubeDroppedModule extends BehaviourModule {
	private boolean value;
	
	public SetVariableGreenCubeDroppedModule(Marvin marvin, boolean value) {
		super(marvin);
		this.value = value;
	}

	@Override
	public boolean execute() {
		getMarvin().getMemoryOpdracht2().greenCubeDelivered = value;
		return true;
	}
}
