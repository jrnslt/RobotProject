package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class ConditionCubeFoundModule extends BehaviourModule {
	private boolean check;
	private MColor color;
	
	public ConditionCubeFoundModule(Marvin marvin, boolean check, MColor color) {
		super(marvin);
		this.check = check;
		this.color = color;
	}

	@Override
	public boolean execute() {
		if (color == Colors.TAPE_RED) {
			return getMarvin().getMemoryOpdracht2().redCubeFound == check;
		} else if (color == Colors.TAPE_GREEN) {
			return getMarvin().getMemoryOpdracht2().greenCubeFound == check;
		} else if (color == Colors.TAPE_BLUE) {
			return getMarvin().getMemoryOpdracht2().blueCubeFound == check;
		}
		return false;
	}
}
