package nl.hva.miw.robot.cohort13;

import behaviour.modules.BehaviourModule;
import lejos.ev3.tools.EV3Control;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import nl.hva.miw.robot.cohort13.factories.MainModuleFactory;
import nl.hva.miw.robot.cohort13.functionality.ClosestColorFinder;
import nl.hva.miw.robot.cohort13.functionality.ColorSensorControl;
import nl.hva.miw.robot.cohort13.functionality.CubeFinder;
import nl.hva.miw.robot.cohort13.functionality.KeyInputControl;
import nl.hva.miw.robot.cohort13.functionality.MotorControl;
import nl.hva.miw.robot.cohort13.functionality.ProximityControl;

public class Marvin {
	private Brick brick;
	private EV3Control ev3Control;
	private BehaviourModule mainModule;		
	
	private ColorSensorControl colorSensorControlA;
	private ColorSensorControl colorSensorControlB;
	public EV3TouchSensor touchSensor;
	private MotorControl motorControl;
	private KeyInputControl keyInputManager;
	private ProximityControl proximityControl;
	
	private ClosestColorFinder closestColorFinder;
	//private CubeFinder cubeFinder;
	
	public MarvinState state;
	
	public Marvin() {	
		brick = LocalEV3.get(); 
		
		this.colorSensorControlB = new ColorSensorControl(this, SensorPort.S3);
		this.colorSensorControlA = new ColorSensorControl(this, SensorPort.S4);
		this.touchSensor = new EV3TouchSensor(SensorPort.S2);
		this.motorControl = new MotorControl(this);
		this.keyInputManager = new KeyInputControl(this);
		this.proximityControl = new ProximityControl(this);
		this.mainModule = new MainModuleFactory().createModule(this);	
		
		this.closestColorFinder = new ClosestColorFinder();
		//this.cubeFinder = new CubeFinder(this);
	}
	
	public Brick getBrick() {
		return brick;
	}
	
	public EV3Control getEV3Control() {
		return ev3Control;
	}
	
	public ColorSensorControl getColorSensorControlA() {
		return colorSensorControlA;
	}
	
	public ColorSensorControl getColorSensorControlB() {
		return colorSensorControlB;
	}
	
	public MotorControl getMotorControl() {
		return motorControl;
	}
	
	public KeyInputControl getKeyInputManager() {
		return keyInputManager;
	}
	
	public ProximityControl getProximityManager() {
		return proximityControl;
	}
	
	public ClosestColorFinder getClosestColorFinder() {
		return closestColorFinder;
	}
	
	/*
	public CubeFinder getCubeFinder() {
		return cubeFinder;
	}
	*/
	
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
