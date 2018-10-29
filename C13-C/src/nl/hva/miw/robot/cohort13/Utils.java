package nl.hva.miw.robot.cohort13;

public class Utils {
	
	public static double sigmoid(float x, float f) {
		double e = Math.E;
		double a = 1.0;
		double b = 1.0 + Math.pow(e, -f * x);
		
		return a/b;
	}
	
	public static double EuclidianDistance(float x1, float y1, float z1, float x2, float y2, float z2) {
		double dx = x2 - x1;
		double dy = y2 - y1;
		double dz = z2 - z1;
		
		double dxs = Math.pow(dx, 2.0);
		double dys = Math.pow(dy, 2.0);
		double dzs = Math.pow(dz, 2.0);
		
		return Math.pow(dxs + dys + dzs, 0.5);
	}
	
	public static float getBiggestFloat(Float... values) {
		float biggest = values[0];
		
		for (Float f : values) {
			if (f.floatValue() > biggest) {
				biggest = f;
			}
		}
		
		return biggest;
	}
}
