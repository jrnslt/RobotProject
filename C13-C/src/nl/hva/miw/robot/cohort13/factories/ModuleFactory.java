package nl.hva.miw.robot.cohort13.factories;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;

public abstract class ModuleFactory {
	public abstract BehaviourModule createModule(Marvin marvin);
}
