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
import nl.hva.miw.robot.cohort13.functionality.ClosestColorFinder;
import nl.hva.miw.robot.cohort13.functionality.CubeFinder;
import nl.hva.miw.robot.cohort13.functionality.KeyInputManager;

public class Marvin {
	private Brick brick;
	private BehaviourModule mainModule;		
	public EV3ColorSensor colorSensorA;
	public EV3ColorSensor colorSensorB;
	public EV3TouchSensor touchSensor;
	public EV3Control ev3Control;

	public MarvinState state;
	private MotorControl motorControl;
	private ClosestColorFinder closestColorFinder;
	private CubeFinder cubeFinder;
	private KeyInputManager keyInputManager;
	private ProximityManager proximityManager;
	
	public Marvin() {	
		brick = LocalEV3.get(); 
		
		//Toewijzing van de poorten aan sensors/motors
		colorSensorB = new EV3ColorSensor(SensorPort.S3);
		touchSensor = new EV3TouchSensor(SensorPort.S2);
		colorSensorA = new EV3ColorSensor(SensorPort.S4);

		this.motorControl = new MotorControl();
		this.closestColorFinder = new ClosestColorFinder();
		this.cubeFinder = new CubeFinder(this);
		this.keyInputManager = new KeyInputManager(this);
		this.proximityManager = new ProximityManager(this);
		
		this.mainModule = new MainModuleFactory().createModule(this);	
	}
	
	public Brick getBrick() {
		return brick;
	}
	
	public MotorControl getMotorControl() {
		return motorControl;
	}
	
	public ClosestColorFinder getClosestColorFinder() {
		return closestColorFinder;
	}
	
	public CubeFinder getCubeFinder() {
		return cubeFinder;
	}
	
	public KeyInputManager getKeyInputManager() {
		return keyInputManager;
	}
	
	public ProximityManager getProximityManager() {
		return proximityManager;
	}
	
	public void incrementState(int amount) {
		// Hoeveel programma's er worden gedraaid.
		int stateSize = MarvinState.getAmountOfStates();		
		int stateNumber = (state.stateNumber + amount) % stateSize;	
		state = MarvinState.getStateByNumber(stateNumber); 
	}
	
	public void run() {						
		//Voert module(s) uit middels execute
		mainModule.execute();		
		keyInputManager.waitForKey(Button.ENTER);
	}
	
	public static void main(String[] args) {
		Marvin marvin = new Marvin(); 	//instantieert Marvin
		marvin.run(); 					//voert de onderstaande void uit
	}
}
