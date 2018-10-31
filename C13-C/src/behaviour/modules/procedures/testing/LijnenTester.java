package behaviour.modules.procedures.testing;

import java.util.ArrayList;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.ColorSensorControl;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class LijnenTester extends BehaviourModule {
	private final long runTime = 10000000;

	public LijnenTester(Marvin marvin) {
		super(marvin);
	}

	@Override
	public boolean execute() {

		ColorSensorControl colorSensorControl = getMarvin().getColorSensorControlDown();
		colorSensorControl.calibrateSensor();

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
		colors.add(Colors.WHITE);
		colors.add(Colors.BLACK);
		colors.add(Colors.GREY);
		colors.add(Colors.DARKGREY);
//		colors.add(Colors.LIGHTGREY);

		boolean redLine = true;
		int redLinesPassed = 0;
		Stopwatch stopWatch = new Stopwatch();
		double numberOfSeconds = 0;

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

			MColor closestColor = getMarvin().getClosestColorFinder().getClosestColor(colors, new MColor("", r, g, b));
			textLCD.drawString("" + closestColor.getColorName(), 1, 6);

			textLCD.drawString("RGB mode", 1, 1);
			textLCD.drawString("" + r, 1, 2);
			textLCD.drawString("" + g, 1, 3);
			textLCD.drawString("" + b, 1, 4);

			if (closestColor == Colors.RED) {
				Sound.beep();
				redLinesPassed++;

				if (redLinesPassed < 2) {
					stopWatch.reset();
				}
				getMarvin().getMotorControl().drive(200, 200); // rechtdoor
				Delay.msDelay(200);
//	       
//				Delay.msDelay(100);
//
				if (redLinesPassed >= 2) {
					numberOfSeconds = stopWatch.elapsed() / 1000;
					break;
				}
			} else if (closestColor == Colors.GREY) { // rechtdoor
				textLCD.drawString("Grijs", 1, 5);
//	        	getMarvin().getMotorControl().drive(-150, 200);
//	        	Delay.msDelay(100);
				getMarvin().getMotorControl().drive(200, 200);
				Delay.msDelay(200);

			} else if (closestColor == Colors.DARKGREY) { // rechtdoor
				textLCD.drawString("Wit", 1, 5);
				getMarvin().getMotorControl().drive(200, 200);
				Delay.msDelay(200);

//					getMarvin().getMotorControl().drive(1, -250);
//					Delay.msDelay(100);
//					getMarvin().getMotorControl().drive(150, 150);
//					Delay.msDelay(100);

			} else if (closestColor == Colors.WHITE) {

				textLCD.drawString("wit", 1, 5);
				getMarvin().getMotorControl().drive(-220, 1);// links
				Delay.msDelay(150);
					getMarvin().getMotorControl().drive(150, 150);
					Delay.msDelay(150);
			} else if (closestColor == Colors.BLACK) {
				textLCD.drawString("zwart", 1, 5);
				getMarvin().getMotorControl().drive(1, -220);// rechts
				Delay.msDelay(150);
					getMarvin().getMotorControl().drive(150, 150);
					Delay.msDelay(150);
			}

		}
		getMarvin().getMotorControl().stop(); // Stop robot
		textLCD.clear(0, 1, 2);
		textLCD.drawString("Tijd: " + numberOfSeconds, 1, 2);

		return true;

	}

}
