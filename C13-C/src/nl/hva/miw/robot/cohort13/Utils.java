package nl.hva.miw.robot.cohort13;

public class Utils {
	
	/**
	 * @param x
	 * @param f
	 * @return the y from sigmoid function
	 */
	public static double sigmoid(float x, float f) {
		double e = Math.E;
		double a = 1.0;
		double b = 1.0 + Math.pow(e, -f * x);
		
		return a/b;
	}
	
	/**
	 * @param x1
	 * @param y1
	 * @param z1
	 * @param x2
	 * @param y2
	 * @param z2
	 * @return the euclidian distance between 2 3D points
	 */
	public static double euclidianDistance(float x1, float y1, float z1, float x2, float y2, float z2) {
		double dx = x2 - x1;
		double dy = y2 - y1;
		double dz = z2 - z1;
		
		double dxs = Math.pow(dx, 2.0);
		double dys = Math.pow(dy, 2.0);
		double dzs = Math.pow(dz, 2.0);
		
		return Math.pow(dxs + dys + dzs, 0.5);
	}
	
	/**
	 * @param values
	 * @return the biggest float in a list of floats
	 */
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
