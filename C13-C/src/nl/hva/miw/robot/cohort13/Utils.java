package nl.hva.miw.robot.cohort13;

public class Utils {
	
	public static double sigmoid(float x, float f) {
		double e = Math.E;
		double a = 1.0;
		double b = 1.0 + Math.pow(e, -f * x);
		
		return a/b;
	}
}
