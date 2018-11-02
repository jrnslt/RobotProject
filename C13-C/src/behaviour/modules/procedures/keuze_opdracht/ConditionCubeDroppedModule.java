package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class ConditionCubeDroppedModule extends BehaviourModule {
	private boolean check;
	private MColor color;
	
	public ConditionCubeDroppedModule(Marvin marvin, boolean check, MColor color) {
		super(marvin);
		this.check = check;
		this.color = color;
	}

	@Override
	public boolean execute() {
		if (color == Colors.TAPE_RED) {
			return getMarvin().getMemoryOpdracht2().redCubeDelivered == check;
		} else if (color == Colors.TAPE_GREEN) {
			return getMarvin().getMemoryOpdracht2().greenCubeDelivered == check;
		} else if (color == Colors.TAPE_BLUE) {
			return getMarvin().getMemoryOpdracht2().blueCubeDelivered == check;
		}
		return false;
	}
}
