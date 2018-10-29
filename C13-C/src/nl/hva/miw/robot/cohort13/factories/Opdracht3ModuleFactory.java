package nl.hva.miw.robot.cohort13.factories;

import behaviour.modules.BehaviourModule;
import behaviour.modules.logic.LeafModule;
import behaviour.modules.logic.LoopModule;
import behaviour.modules.logic.SequenceModule;
import behaviour.modules.logic.SequenceUntilFailModule;
import behaviour.modules.procedures.uitbreiding.ResetVariablesOpdracht3Module;
import behaviour.modules.procedures.uitbreiding.RijdenNaarMiddenOpdracht3Module;
import behaviour.modules.procedures.uitbreiding.VictoryDanceOpdracht3Module;
import nl.hva.miw.robot.cohort13.Marvin;

public class Opdracht3ModuleFactory extends ModuleFactory {

	@Override
	public BehaviourModule createModule(Marvin marvin) {
		LeafModule leafModule = new LeafModule(marvin);
			SequenceModule sequenceModule = new SequenceModule(marvin);
				ResetVariablesOpdracht3Module resetVariableOpdracht3Module = new ResetVariablesOpdracht3Module(marvin);
				LoopModule loopModuleA = new LoopModule(marvin);
					SequenceUntilFailModule sequenceUntilFailModule = new SequenceUntilFailModule(marvin);
				RijdenNaarMiddenOpdracht3Module rijdenNaarMiddenModule = new RijdenNaarMiddenOpdracht3Module(marvin);
				VictoryDanceOpdracht3Module victoryDanceOpdracht3Module = new VictoryDanceOpdracht3Module(marvin);
		
		return leafModule;
	}

}
