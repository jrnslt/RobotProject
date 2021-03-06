package nl.hva.miw.robot.cohort13.functionality;

import nl.hva.miw.robot.cohort13.Marvin;

/*
 * abstrace module met een connectie met marvin
 */
public abstract class MarvinComponent {
	private Marvin marvin;
	
	public MarvinComponent(Marvin marvin) {
		this.marvin = marvin;
	}
	
	public Marvin getMarvin() {
		return marvin;
	}
}
