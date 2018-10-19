package nl.hva.miw.robot.cohort13;

import behaviour.modules.SequenceModule;
import behaviour.modules.WelcomeModule;
import behaviour.modules.parcour.ParcourSoundModule;
import behaviour.modules.parcour.ParcoursModule;
import behaviour.modules.parcour.ParcoursModuleRGB;
import behaviour.modules.sound.BeepModule;
import behaviour.modules.testing.ColorSensorTesterModule;
import behaviour.modules.testing.TestProcedureModule;
import behaviour.modules.testing.WaitForKeyModule;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Marvin {
	private Brick brick;
	private SequenceModule mainModule;
	public EV3ColorSensor colorSensorA;
	public EV3ColorSensor colorSensorB;
	public EV3TouchSensor touchSensor;

	public RegulatedMotor groteMotorLinks;
	public RegulatedMotor groteMotorRechts;
	public RegulatedMotor kleineMotorArm;
	public RegulatedMotor groteMotor4;
		
	//public SoundProducer soundProducer;
	public ProximitySensor proximitySensor;
	
	public Marvin() {
		super(); 
		brick = LocalEV3.get();
		initInputOutput();
		
		mainModule = new SequenceModule(this);
		mainModule.addModule(new WelcomeModule(this));
	//	mainModule.addModule(new ColorSensorTesterModule(this, "Tester"));
		mainModule.addModule(new ParcoursModuleRGB(this));
		//mainModule.addModule(new ParcoursModule(this));
		
//		mainModule.addModule(
//				new SequenceModule(this).
//					addModule(new WaitForKeyModule(this)).
//					addModule(new ParcourSoundModule(this)).
//					addModule(new ParcoursModule(this)).
//					addModule(new BeepModule(this)).
//					addModule(new WaitForKeyModule(this))
//		);	 
		
		//mainModule.addModule(new ZwartEnWit(this));
	//	mainModule.addModule(new TestProcedureModule(this));
	}
	
	private void initInputOutput() {
	//	proximitySensor = new ProximitySensor(this); 
		colorSensorB = new EV3ColorSensor(SensorPort.S3);
	//	touchSensor = new EV3TouchSensor(SensorPort.S3);
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
