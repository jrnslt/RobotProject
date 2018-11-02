package nl.hva.miw.robot.cohort13.functionality;

import java.util.ArrayList;

import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.Utils;
import nl.hva.miw.robot.cohort13.resources.Colors;

public final class ClosestColorFinder extends MarvinComponent {
		
	public ClosestColorFinder(Marvin marvin) {
		super(marvin);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param 
	 * @param 
	 * @return zelfde als @getClosestColor(ArrayList<MColor> colors, float r, float g, float b)
	 * 		   maar de gelezen kleur is meegegeven als @MColor object inplaats van losse r,g,b waardes 	
	 */
	public MColor getClosestColor(ArrayList<MColor> colors, MColor color) {
		return getClosestColor(colors, color.getRed(), color.getGreen(), color.getBlue());
	}
	
	/**
	 * @param colors: een lijst met kleuren die alle kleuren bevat die mogelijk terug gegeven kunnen worden door deze method
	 * @param r: rood waarde
	 * @param g: groen waarde
	 * @param b: blauw waarde
	 * @return de kleur uit de lijst die het dichtste in de buurt komt van de parameter r, g, b waardes
	 */
	public MColor getClosestColor(ArrayList<MColor> colors, float r, float g, float b) {
		MColor closestColor = null;
		
		for (MColor color : colors) {
			
			if (closestColor == null) {
				closestColor = color;
			}
			
			double distanceCurrent = Utils.euclidianDistance(color.getRed(), color.getGreen(), color.getBlue(), r, g, b);
			double distanceShortest = Utils.euclidianDistance(closestColor.getRed(), closestColor.getGreen(), closestColor.getBlue(), r, g, b);
			
			if (distanceCurrent < distanceShortest) {
				closestColor = color;
			}
		}
		
		return closestColor;
	}
}
