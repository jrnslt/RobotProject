package behaviour.modules.logic;

import nl.hva.miw.robot.cohort13.Marvin;

/**
 * calls execute for child module and always returns true
 *
 */
public class SucceederModule extends LeafModule {

	public SucceederModule(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		super.execute();
		return true;
	}
}
