package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;

public class ResetVariablesOpdracht2Module extends BehaviourModule {

	public ResetVariablesOpdracht2Module(Marvin marvin) {
		super(marvin);
	}

	/**
	 * 
	 * reset de variabelen
	 * 
	 */
	@Override
	public boolean execute() {
		getMarvin().getMemoryOpdracht2().reset();
		return true;
	}
}
