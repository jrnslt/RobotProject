package nl.hva.miw.robot.cohort13.functionality;

import java.util.ArrayList;

import nl.hva.miw.robot.cohort13.Utils;
import nl.hva.miw.robot.cohort13.resources.Colors;

public final class ClosestColorFinder {
		
	public MColor getClosestColor(ArrayList<MColor> colors, MColor color) {
		return getClosestColor(colors, color.getRed(), color.getGreen(), color.getBlue());
	}
	
	public MColor getClosestColor(ArrayList<MColor> colors, float r, float g, float b) {
		MColor closestColor = null;
		
		for (MColor color : colors) {
			MColor brightenedColor = new MColor(color);
			
			if (closestColor == null) {
				closestColor = brightenedColor;
			}
			
			double distanceCurrent = Utils.EuclidianDistance(brightenedColor.getRed(), brightenedColor.getGreen(), brightenedColor.getBlue(), r, g, b);
			double distanceShortest = Utils.EuclidianDistance(closestColor.getRed(), closestColor.getGreen(), closestColor.getBlue(), r, g, b);
			
			if (distanceCurrent < distanceShortest) {
				closestColor = brightenedColor;
			}
		}
		
		return closestColor;
	}
}
