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

public class ParcoursModuleRgbCalibrate extends BehaviourModule {
	//klassen die nodig zijn
	private final long runTime = 30000;
	Stopwatch stopWatch;
	ColorSensorControl colorSensorControl;
	EV3ColorSensor colorSensor;
	TextLCD textLCD;
	MotorControl motorcontrol;

	public ParcoursModuleRgbCalibrate(Marvin marvin) {
		super(marvin);
		this.colorSensorControl = getMarvin().getColorSensorControlDown();
		this.colorSensor = colorSensorControl.getColorSensor();
		this.textLCD = getMarvin().getBrick().getTextLCD();
		this.motorcontrol = getMarvin().getMotorControl();

	}

	@Override
	public boolean execute() {
		//calibrate sensor
		colorSensorControl.calibrateSensor();
		Delay.msDelay(3000);

		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();

		SensorMode sensorModeRGB = colorSensor.getRGBMode();
		colorSensor.setFloodlight(Color.WHITE);
		colorSensor.setCurrentMode(sensorModeRGB.getName());

		float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
		textLCD.setAutoRefresh(false);

		
		ArrayList<MColor> colors = new ArrayList<>();
		colors.add(Colors.RED);
		colors.add(Colors.BLUE);
		colors.add(Colors.WHITE);
		colors.add(Colors.BLACK);
		colors.add(Colors.GREY);

		//number of passed red lines
		int timesRed = 0;

		while (lastTime - startTime < runTime) {
			// start drive
			motorcontrol.driveForward(100, 100);

			lastTime = System.currentTimeMillis();
			sensorModeRGB.fetchSample(sampleRGB, 0);

			textLCD.setAutoRefresh(false);
			textLCD.refresh();
			textLCD.clear();

			float r = sampleRGB[0]; // rood
			float g = sampleRGB[1]; // groen
			float b = sampleRGB[2]; // blauw

			String sR = String.format("R: %.2f", r);
			String sG = String.format("G: %.2f", g);
			String sB = String.format("B: %.2f ", b);

			float nr = colorSensorControl.getRed(r);
			float ng = colorSensorControl.getGreen(g);
			float nb = colorSensorControl.getBlue(b);

			MColor closestColor = getMarvin().getClosestColorFinder().getClosestColor(colors,
					new MColor("", nr, ng, nb));

			textLCD.drawString("RGB mode", 1, 1);
			textLCD.drawString(sR, 1, 2);
			textLCD.drawString(sG, 1, 3);
			textLCD.drawString(sB, 1, 4);

			if (closestColor == Colors.RED) {
				
				timesRed++;
				Sound.beep();

				// if number redlines passed > 1 the robot has to stop, else start the stopwatch
				if (timesRed > 1) {
					textLCD.drawString("red line", 1, 5);
					motorcontrol.stop();
					textLCD.drawString("tijd: " + stopWatch.elapsed() / 1000.0f, 1, 5);
					Delay.msDelay(10000);
					break;

				
				} else {
					stopWatch = new Stopwatch();
					Delay.msDelay(1000);
				}
				
				//drive and respond to colorvalues
			} else if (closestColor == Colors.BLACK) {
				textLCD.drawString("zwart", 1, 5);
				motorcontrol.drive(1, -220);
				Delay.msDelay(150);
				motorcontrol.drive(100, 100);
				Delay.msDelay(150);
			} else if (closestColor == Colors.DARKGREY) { // rechtdoor
				textLCD.drawString("donkergrijs", 1, 5);
				motorcontrol.drive(200, 200);
				Delay.msDelay(200);

			} else if (closestColor == Colors.GREY) { // rechtdoor
				textLCD.drawString("Grijs", 1, 5);
				motorcontrol.drive(200, 200);
				Delay.msDelay(200);
			} else if (closestColor == Colors.WHITE) {
				textLCD.drawString("wit", 1, 5);
				motorcontrol.drive(-220, 1);
				Delay.msDelay(150);
				motorcontrol.drive(100, 100);
				Delay.msDelay(150);
			}
		}

		//motors have to stop
		motorcontrol.stop();

		return false;
	}

}