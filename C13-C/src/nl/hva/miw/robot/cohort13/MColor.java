package nl.hva.miw.robot.cohort13;

public class MColor {
	private String colorName;
	private float r;
	private float g;
	private float b;

	public MColor(MColor color) {
		this.colorName = color.getColorName();
		this.r = color.r;
		this.g = color.g;
		this.b = color.b;
	}
	
	public MColor(String colorName) {
		this.colorName = colorName;
	}
	
	public MColor(String colorName, float r, float g, float b) {
		this.colorName = colorName;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public String getColorName() {
		return colorName;
	}
	
	public float getRed() {
		return r;
	}
	
	public float getGreen() {
		return g;
	}
	
	public float getBlue() {
		return b;
	}
	
	/**
	 * @param f
	 * @return normalised color
	 */
	public MColor normalize(float f) {	
		float rA = this.getRed();
		float gA = this.getGreen();
		float bA = this.getBlue();
			
		if (f != 0) {
			rA = rA/f;
			gA = gA/f;
			bA = bA/f;
		}
		
		return new MColor(getColorName(), rA, gA, bA);
	}
	
	
	/**
	 * @return normalised color
	 */
	public MColor normalize() {	
		float rA = this.getRed();
		float gA = this.getGreen();
		float bA = this.getBlue();
		
		float biggest = Utils.getBiggestFloat(rA, gA, bA);
		
		if (biggest != 0) {
			rA = rA/biggest;
			gA = gA/biggest;
			bA = bA/biggest;
		}
		
		return new MColor(getColorName(), rA, gA, bA);
	}
}
