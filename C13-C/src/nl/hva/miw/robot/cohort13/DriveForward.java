package nl.hva.miw.robot.cohort13;

import lejos.hardware.Sound;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

public class DriveForward {

	
	public void geefGas() {

    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);
    motorA.setPower(100);
    motorD.setPower(100);
	Sound.twoBeeps(); 
    motorA.forward();
    motorD.forward();
	
	}
	
}
