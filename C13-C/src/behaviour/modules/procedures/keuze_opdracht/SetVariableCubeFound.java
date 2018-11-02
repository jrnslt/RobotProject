package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class SetVariableCubeFound extends BehaviourModule {
	private boolean value;
	private MColor color;
	
	public SetVariableCubeFound(Marvin marvin, boolean value, MColor color) {
		super(marvin);
		this.value = value;
		this.color = color;
	}

	@Override
	public boolean execute() {
		if (color == Colors.TAPE_RED) {
			getMarvin().getMemoryOpdracht2().redCubeFound = value;
		} else if (color == Colors.TAPE_GREEN) {
			getMarvin().getMemoryOpdracht2().greenCubeFound = value;
		} else if (color == Colors.TAPE_BLUE) {
			getMarvin().getMemoryOpdracht2().blueCubeFound = value;
		}
		
		return true;
	}
}
