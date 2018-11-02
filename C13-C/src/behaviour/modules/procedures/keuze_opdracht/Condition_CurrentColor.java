package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;

public class Condition_CurrentColor extends BehaviourModule {
	private MColor color;
	
	public Condition_CurrentColor(Marvin marvin, MColor color) {
		super(marvin);
		this.color = color;
	}

	@Override
	public boolean execute() {
		return getMarvin().getMemoryOpdracht2().currentColor == color;
	}

}
