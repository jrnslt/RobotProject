package nl.hva.miw.robot.cohort13;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Drive {

    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    UnregulatedMotor motorD = new UnregulatedMotor(MotorPort.D);
    
    RegulatedMotor motorB = Motor.B;
    		
    		//new RegulatedMotor(MotorPort.B);
    

public void goForward() {
	motorA.setPower(100);
    motorD.setPower(100);
    motorA.forward();
    motorD.forward();
	
}
	
}
