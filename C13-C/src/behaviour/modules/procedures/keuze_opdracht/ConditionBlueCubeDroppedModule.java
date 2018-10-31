package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;

public class ConditionBlueCubeDroppedModule extends BehaviourModule {
	private boolean check;
	
	public ConditionBlueCubeDroppedModule(Marvin marvin, boolean check) {
		super(marvin);
		this.check = check;
	}

	@Override
	public boolean execute() {
		return getMarvin().getMemoryOpdracht2().blueCubeDelivered == check;
	}
}