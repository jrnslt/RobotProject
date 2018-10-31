package behaviour.modules.procedures.uitbreiding;

import java.util.ArrayList;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.ColorSensorControl;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class RegenBoogChecker extends BehaviourModule {
	private final long runTime = 10000000;
	
	public RegenBoogChecker(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {
		ColorSensorControl colorSensorControl = getMarvin().getColorSensorControlDown();
		
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		EV3ColorSensor colorSensor = colorSensorControl.getColorSensor();

		SensorMode sensorModeRGB = colorSensor.getRGBMode();
		colorSensor.setFloodlight(Color.WHITE);
		colorSensor.setCurrentMode(sensorModeRGB.getName());

		float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
		Sound.beep();
	
        ArrayList<MColor> colors = new ArrayList<>();
        colors.add(Colors.RED);
        colors.add(Colors.YELLOW);
        colors.add(Colors.CHARTREUSE);
        colors.add(Colors.GREEN);
        colors.add(Colors.CYAN);
        colors.add(Colors.LIGHT_BLUE);
        colors.add(Colors.LIGHT_PURPLE);
        colors.add(Colors.PINK);
        colors.add(Colors.WHITE);
        colors.add(Colors.BLACK);
        
		while (lastTime - startTime < runTime) {
			lastTime = System.currentTimeMillis();
			sensorModeRGB.fetchSample(sampleRGB, 0);
			
	        textLCD.setAutoRefresh(false);
	        textLCD.refresh();
	        textLCD.clear();

			float r = sampleRGB[0]; // rood
			float g = sampleRGB[1]; // groen
			float b = sampleRGB[2]; // blauw
			
			r = colorSensorControl.getRed(r);
			g = colorSensorControl.getGreen(g);
			b = colorSensorControl.getBlue(b);
			
	        MColor closestColor = getMarvin().getClosestColorFinder().
	        		getClosestColor(colors, new MColor("", r, g, b));
	        textLCD.drawString("" + r, 1, 2);
	        textLCD.drawString("" + g, 1, 3);
	        textLCD.drawString("" + b, 1, 4);  
	        textLCD.drawString(closestColor.getColorName(), 1, 5);
		}
		
		getMarvin().getMotorControl().stop();	//Stop robot
		
		return true;
	}

}