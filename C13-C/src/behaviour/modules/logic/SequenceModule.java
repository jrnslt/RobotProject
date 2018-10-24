package behaviour.modules.logic;

import java.util.ArrayList;

import behaviour.modules.BehaviourModule;
import nl.hva.miw.robot.cohort13.Marvin;


/**
 * executes all submodules in sequence
 * returns true if all child modules have returned true after loop
 * returns false if any child module have returned false after loop
 */
public class SequenceModule extends BehaviourModule { 
	private final ArrayList<BehaviourModule> modules;
	
	public SequenceModule(Marvin marvin) {
		super(marvin);
		this.modules = new ArrayList<>();
	}
	
	public SequenceModule addModule(BehaviourModule module) { //voegt een module toe aan de sequence arraylist
		this.modules.add(module);
		return this;
	}
	
	@Override
	public boolean execute() { // NOOT: Execute is standaard false en geeft true terug wanneer uitgevoerd.
		boolean result = true;
		
		for (BehaviourModule module : modules) {
			if (!module.execute()) {
				result = false;
			}
		}
		
		return result;
	}
}
