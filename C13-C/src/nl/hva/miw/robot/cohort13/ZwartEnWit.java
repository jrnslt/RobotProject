package nl.hva.miw.robot.cohort13;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Calibrate;
import lejos.robotics.Color;

public class ZwartEnWit implements Calibrate {

	public int test(Marvin marvin) {
		long lastTime = 0;
		int testCount = 0;

		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
		SensorMode sensorModeRGB = colorSensor.getRGBMode();
		float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
		TextLCD textLCD = marvin.getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
		Sound.beep();

		while (testCount < 40) {
			long currentTime = System.currentTimeMillis();
			sensorModeRGB.fetchSample(sampleRGB, 0);
			if (currentTime - lastTime > 1000) {
				lastTime = currentTime;
				textLCD.refresh();
				textLCD.clear();
				float r = sampleRGB[0]; // rood
				float r2 = r * 100;
				if (r2 < 7) {
					System.out.printf("Kleur is \nzwart%.3f ", r2);
					testCount++;
					return 1;
				} else if (r2 >= 7) {
					System.out.printf("Kleur is \nwit%.3f ", r2);
					testCount++;
					return 2;
				} else
					return 3;
			}

		}
		return 0;
	}

	@Override
	public void startCalibration() {
	}

	@Override
	public void stopCalibration() {
	}
}
