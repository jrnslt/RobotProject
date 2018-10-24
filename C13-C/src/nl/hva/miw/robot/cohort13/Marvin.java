package nl.hva.miw.robot.cohort13;

import behaviour.modules.BehaviourModule;
import behaviour.modules.logic.LoopModule;
import behaviour.modules.logic.SequenceModule;
import behaviour.modules.procedures.console.ConsoleModule;
import behaviour.modules.procedures.exit.GoodbyeModule;
import behaviour.modules.procedures.keuze_opdracht.KeuzeOpdrachtModule;
import behaviour.modules.procedures.parcour.ParcourSoundModule_End;
import behaviour.modules.procedures.parcour.ParcourSoundModule_Start;
import behaviour.modules.procedures.parcour.ParcoursModule;
import behaviour.modules.procedures.testing.ColorSensorTesterModule;
import behaviour.modules.procedures.testing.ProximitySensorTesterModule;
import behaviour.modules.procedures.welcome.WelcomeModule;
import behaviour.modules.sound.BeepModule;
import lejos.ev3.tools.EV3Control;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.remote.ev3.RMIMenu;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.factories.MainModuleFactory;

public class Marvin {
	private Brick brick;
	private BehaviourModule mainModule;		
	public EV3ColorSensor colorSensorA;
	public EV3ColorSensor colorSensorB;
	public EV3TouchSensor touchSensor;
	public EV3Control ev3Control;
	public EV3IRSensor proximitySensor;

	public RegulatedMotor groteMotorLinks;
	public RegulatedMotor groteMotorRechts;
	public RegulatedMotor kleineMotorArm;
	public RegulatedMotor groteMotor4;

	public MarvinState state;
	
	public Marvin() {
		super(); 
		
		brick = LocalEV3.get(); // welke poort voor wat gebruikt wordt.
		initInputOutput();
		
		mainModule = new MainModuleFactory().createModule(this);						
	}
	
	public void incrementState(int amount) {
		int stateSize = MarvinState.getAmountOfStates();		// Hoeveel programma's er worden gedraaid.
		int stateNumber = (state.stateNumber + amount) % stateSize;	
		state = MarvinState.getStateByNumber(stateNumber); 
	}
	
	private void initInputOutput() {		//Toewijzing van de poorten aan sensors/motors
		proximitySensor = new EV3IRSensor(SensorPort.S1);
		colorSensorB = new EV3ColorSensor(SensorPort.S3);
		touchSensor = new EV3TouchSensor(SensorPort.S2);
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
		Marvin marvin = new Marvin(); 	//instantieert Marvin
		marvin.run(); 					//voert de onderstaande void uit
	}
	
	private void run() {				//Voert module(s) uit middels execute
//		mainModule.execute();		
//		waitForKey(Button.ENTER);
//		ParcoursModule parcoursmodule = new ParcoursModule(this);	
//		parcoursmodule.execute();
		KeuzeOpdrachtModule keuzeopdrachtmodule = new KeuzeOpdrachtModule(this);
		keuzeopdrachtmodule.execute();
//		ProximitySensorTesterModule test = new ProximitySensorTesterModule(this, "test");
//		test.execute();
		
		
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
