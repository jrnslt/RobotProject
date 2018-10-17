package nl.hva.miw.robot.cohort13;

import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

public class Drive {

    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);


public void goForward() {
	motorA.setPower(100);
    motorD.setPower(100);
    motorA.forward();
    motorD.forward();
	
}
	
}
