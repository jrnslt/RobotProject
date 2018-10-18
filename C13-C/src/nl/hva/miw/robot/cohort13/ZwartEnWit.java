package nl.hva.miw.robot.cohort13;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Calibrate;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class ZwartEnWit extends BehaviourModule implements Calibrate {

	public ZwartEnWit(Marvin marvin) {
		super(marvin);
	}

	public boolean execute() {
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		EV3ColorSensor colorSensor = marvin.colorSensorA;
		SensorMode sensorModeRed = colorSensor.getRedMode();
		colorSensor.setFloodlight(Color.RED);
		colorSensor.setCurrentMode(sensorModeRed.getName());

		float[] sampleRed = new float[sensorModeRed.sampleSize()];

		TextLCD textLCD = marvin.getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
		Sound.beep();

		while (lastTime - startTime < 30000) {
			lastTime = System.currentTimeMillis();
			sensorModeRed.fetchSample(sampleRed, 0);
			textLCD.refresh();
			textLCD.clear();
			float r = sampleRed[0]; // rood
			
			if (r < 0.30) {
				System.out.printf("Kleur is \nzwart %.3f ", r);
			} else if (r < 0.50) {
				System.out.printf("Kleur is \n zwart - wit %.3f ", r);
			} else if (r > 0.50) {
				System.out.printf("Kleur is \n wit %.3f ", r);
			}
		}
        Delay.msDelay(500);
		return true;
	}

	@Override
	public void startCalibration() {
	}

	@Override
	public void stopCalibration() {
	}

}
