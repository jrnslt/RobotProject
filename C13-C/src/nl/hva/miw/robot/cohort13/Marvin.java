package nl.hva.miw.robot.cohort13;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Delay;

public class Marvin {
	
	Brick brick;
	
	public Marvin() {
		super();
		brick = LocalEV3.get();
	}
	
	public static void main(String[] args) {
		Marvin marvin = new Marvin();
		marvin.run();
	}
	
	private void run() {
		TextLCD display = brick.getTextLCD();
		display.drawString("Welkom", 0, 3);

		display.drawString("Team C!", 0, 4);
		
		SoundTester soundTester = new SoundTester();
		soundTester.test(this);
		
		//ColorSensorTester colorSensorTester = new ColorSensorTester();
		//colorSensorTester.test(this);
		
		//TouchSensorTester touchSensorTester = new TouchSensorTester();
		//touchSensorTester.test(this);
		
		waitForKey(Button.ENTER);
	}
	
	public void waitForKey(Key key) {
		while(key.isUp()) {
			Delay.msDelay(100);
		}
		while(key.isDown()) {
			Delay.msDelay(100);
		}
	}
}
