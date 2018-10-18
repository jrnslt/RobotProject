package nl.hva.miw.robot.cohort13;

import behaviour.modules.GroupModule;
import behaviour.modules.ParcoursModule;
import behaviour.modules.WelcomeModule;
import behaviour.modules.testing.ColorSensorTesterModule;
import behaviour.modules.testing.TestProcedureModule;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Marvin  implements HardwareInterface  {
	private Brick brick;
	private GroupModule mainModule;
	public EV3ColorSensor colorSensorA;
	public EV3ColorSensor colorSensorB;
	public EV3IRSensor proximitySensor;
	public EV3TouchSensor touchSensor;

	public RegulatedMotor groteMotorLinks;
	public RegulatedMotor groteMotorRechts;
	public RegulatedMotor kleineMotorArm;
	public RegulatedMotor groteMotor4;
	
	public SoundProducer soundProducer;
	
	public Marvin() {
		super(); 
		brick = LocalEV3.get();
		initInputOutput();
		
		
		mainModule = new GroupModule(this);

		mainModule.addModule(new WelcomeModule(this));
		mainModule.addModule(new ParcoursModule(this));
		
//		mainModule.addModule(new ZwartEnWit(this));
		mainModule.addModule(new ColorSensorTesterModule(this, "kleuren testen"));
		
		
	//	mainModule.addModule(new TestProcedureModule(this));
	}
	
	private void initInputOutput() {
		//proximitySensor = new EV3IRSensor(SensorPort.S1);
		colorSensorB = new EV3ColorSensor(SensorPort.S2);
		touchSensor = new EV3TouchSensor(SensorPort.S3);
		colorSensorA = new EV3ColorSensor(SensorPort.S4);

		groteMotorLinks = Motor.A;
		groteMotorRechts = Motor.B;
		kleineMotorArm = Motor.C;
		groteMotor4 = Motor.D;
	}
	
	public Brick getBrick() {
		return brick;
	}
	
	public static void main(String[] args) {
		Marvin marvin = new Marvin();
		marvin.run();
	}
	
	private void run() {
		
		
		mainModule.execute();		
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
