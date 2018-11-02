package behaviour.modules.procedures.parcour;

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
import nl.hva.miw.robot.cohort13.functionality.MotorControl;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class ParcoursModuleRGB extends BehaviourModule {
	EV3ColorSensor colorSensor;
	Stopwatch stopWatch;
	ColorSensorControl colorSensorControl;
	SensorMode sensorModeRGB;
	TextLCD textLCD;
	MotorControl motorcontrol;

	private final long runTime = 1000000;
	private final double regularBlack = 0.6;
	private final double blackWhite = 1.0;
	private final double regularWhite = 1.2;
	private final double extremeWhite = 1.5;

	public ParcoursModuleRGB(Marvin marvin) {
		super(marvin);
		this.colorSensor = getMarvin().getColorSensorControlDown().getColorSensor();
		this.colorSensorControl = getMarvin().getColorSensorControlDown();
		this.textLCD = getMarvin().getBrick().getTextLCD();
		this.stopWatch = null;
	}

	@Override
	public boolean execute() {
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();

		// sensor calibreren
		colorSensorControl.calibrateSensor();
		
		
		//kleuren die hij moet kennen
		ArrayList<MColor> colors = new ArrayList<>();
		colors.add(Colors.RED);
		colors.add(Colors.BLUE);
		colors.add(Colors.WHITE);
		colors.add(Colors.BLACK);

		// Sensor in RGB modus zetten;
		sensorModeRGB = colorSensor.getRGBMode();
		colorSensor.setFloodlight(Color.WHITE);
		colorSensor.setCurrentMode(sensorModeRGB.getName());

		//waarden opslaan
		float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
		textLCD.setAutoRefresh(false);

		Sound.beep();
		motorcontrol.drive(200, 200);
		int timesRed = 0;

		//hij blijft rijden tot de tijd voorbij is
		//TODO stopwatch toevoegen
		
	
		
		while (lastTime - startTime < runTime) {

			lastTime = System.currentTimeMillis();
			sensorModeRGB.fetchSample(sampleRGB, 0);

			textLCD.setAutoRefresh(false);
			textLCD.refresh();
			textLCD.clear();

			float r = sampleRGB[0]; // rood
			float r2 = r * 10;
			float g = sampleRGB[1]; // groen
			float g2 = g * 10;
			float b = sampleRGB[2]; // blauw
			float b2 = b * 10;

			float nr = colorSensorControl.getRed(r);
			float ng = colorSensorControl.getGreen(g);
			float nb = colorSensorControl.getBlue(b);

			String sR = String.format("R: ", nr);
			String sG = String.format("G: ", ng);
			String sB = String.format("B: ", nb);

			MColor closestColor = getMarvin().getClosestColorFinder().getClosestColor(colors,
					new MColor("", nr, ng, nb));

			textLCD.drawString("RGB mode", 1, 1);
			textLCD.drawString(sR, 1, 2);
			textLCD.drawString(sG, 1, 3);
			textLCD.drawString(sB, 1, 4);

			// checken of hij een rode lijn ziet
			if (closestColor == Colors.RED) {
				timesRed++;
				Sound.beep();

				// als de robot de lijn voor de 2e keer ziet, moet hij stoppen, anders moet de
				// stopwatch in gaan

				if (timesRed > 1) {
					textLCD.drawString("red 123", 1, 5);
					motorcontrol.stop();
					textLCD.drawString("tijd: " + stopWatch.elapsed() / 1000.0f, 1, 5);
					Delay.msDelay(10000);
					break;

				} else {
					stopWatch = new Stopwatch();
					Delay.msDelay(1000);
				}
			}

			// vaste waarden
			else if (r2 > extremeWhite) {
				textLCD.drawString(String.format("W %.3f / %.3f", r2, b2), 1, 5);
				motorcontrol.drive(-100, 250);// Get More Left
				Delay.msDelay(150);

			} else if (r2 > regularWhite) {
				motorcontrol.drive(1, 200);// Go Left
				Delay.msDelay(150);
				textLCD.drawString(String.format("WWZ  %.3f / %.3f", r2, b2), 1, 5);

			} else if (r2 > blackWhite) {
				textLCD.drawString(String.format("ZW  %.3f / %.3f", r2, b2), 1, 5);
				motorcontrol.drive(300, 300); // go straight on
				Delay.msDelay(150);

			} else if (r2 > regularBlack) {
				textLCD.drawString(String.format("Z  %.3f / %.3f", r2, b2), 1, 5);
				motorcontrol.drive(200, 1); // Go More Right
				Delay.msDelay(150);

			} else if (r2 < regularBlack) {
				motorcontrol.drive(250, -100); // Get More Right
				Delay.msDelay(150);
			}

		}

		motorcontrol.stop();

		return true;
	}

}