package behaviour.modules.procedures.keuze_opdracht;

import java.util.ArrayList;

import behaviour.modules.BehaviourModule;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.ColorSensorControl;
import nl.hva.miw.robot.cohort13.resources.Colors;

/*
 * this classe is an abstract class where the ground colors where read from the sensor.
 * The child classes inherent the method.
 */
public abstract class GroundColorReaderModule extends BehaviourModule {
	private ArrayList<MColor> colors;
	private String moduleName;
	public MColor color;
	private String n;
	
	public GroundColorReaderModule(Marvin marvin, String moduleName, MColor color, String n) {
		super(marvin);
		this.moduleName = moduleName;
		
		colors = new ArrayList<>();
		colors.add(Colors.TAPE_RED);
		colors.add(Colors.TAPE_GREEN);
		colors.add(Colors.TAPE_BLUE);
		colors.add(Colors.WHITE);
		colors.add(Colors.BLACK);
		
		this.color = color;
		this.n = n;
	}

	public ArrayList<MColor> getColors(){	
		return colors;
	}
	
	public MColor readColor(ArrayList<MColor> colors) {
		ColorSensorControl colorSensorControl = getMarvin().getColorSensorControlDown();
		EV3ColorSensor colorSensor = colorSensorControl.getColorSensor();

		SensorMode sensorModeRGB = colorSensor.getRGBMode();
		
		float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
		sensorModeRGB.fetchSample(sampleRGB, 0);
		
		float r = sampleRGB[0]; // rood
		float g = sampleRGB[1]; // groen
		float b = sampleRGB[2]; // blauw
		
		r = colorSensorControl.getRed(r);
		g = colorSensorControl.getGreen(g);
		b = colorSensorControl.getBlue(b);
		
		MColor closestColor = getMarvin().getClosestColorFinder().getClosestColor(colors, new MColor("", r, g, b));
		
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
		textLCD.refresh();
		textLCD.clear();
		
		textLCD.drawString("n: " + n , 1, 1);
		textLCD.drawString(moduleName, 1, 1);
        textLCD.drawString("" + r, 1, 2);
        textLCD.drawString("" + g, 1, 3);
        textLCD.drawString("" + b, 1, 4);  
        textLCD.drawString(closestColor.getColorName(), 1, 5);
        if (color != null) {
        	textLCD.drawString("looking : " + color.getColorName(), 1, 6);
        }
        
        
        /*
        if (getMarvin().getMemoryOpdracht2().currentColor != null) {
        	textLCD.drawString(getMarvin().getMemoryOpdracht2().currentColor.getColorName(), 1, 6);
        }
        */
		
		return closestColor;
	}
}
