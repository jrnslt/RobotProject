package behaviour.modules.logic;

import nl.hva.miw.robot.cohort13.Marvin;

/**
 * 
 * module with 1 submodule
 * returns the inverted value that is returned by the sub module
 *
 */
public class InverterModule extends LeafModule {

	public InverterModule(Marvin marvin) {
		super(marvin);
	}
	
	@Override
	public boolean execute() {
		return !super.execute();
	}
}
