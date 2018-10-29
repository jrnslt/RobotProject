package behaviour.modules.logic;

import nl.hva.miw.robot.cohort13.Marvin;

/**
 * always return true
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
