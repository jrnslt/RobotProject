package nl.hva.miw.robot.cohort13;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Marvin {
	
	Brick brick;

	public Marvin() {
		super(); 
		brick = LocalEV3.get();
	}
	
	public static void main(String[] args) {
		Marvin marvin = new Marvin();
		marvin.startMotor();
	
		marvin.run();
		
		marvin.startMotor();
	}
	
	private void run() {
		TextLCD display = brick.getTextLCD();
		display.drawString("Welkom", 0, 3);
		display.drawString("Team Charlie!", 0, 4);		
		ColorSensorTester colorSensorTester = new ColorSensorTester();
		colorSensorTester.test(this);
		
		Sound.buzz();
		Sound.twoBeeps(); 
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
	
	
	
	
	public void startMotor() {
		
        UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
        UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);
        motorA.setPower(100);
        motorD.setPower(100);
		Sound.twoBeeps(); 
        motorA.forward();
        motorD.forward();

	}
	
	
}
