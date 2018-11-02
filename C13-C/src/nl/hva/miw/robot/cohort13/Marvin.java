package nl.hva.miw.robot.cohort13;

import behaviour.modules.BehaviourModule;
import behaviour.modules.general.Rotate45DegreesCounterClockwiseModule;
import behaviour.modules.procedures.keuze_opdracht.DriveBackwardsModule;
import behaviour.modules.procedures.keuze_opdracht.DriveForwardModule;
import behaviour.modules.procedures.keuze_opdracht.DropCubeModule;
import behaviour.modules.procedures.keuze_opdracht.FindCubeDropOnSpotModule;
import behaviour.modules.procedures.keuze_opdracht.FollowLineForAShortWhile;
import behaviour.modules.procedures.keuze_opdracht.FollowLineUntilCubeIsFoundModule;
import behaviour.modules.procedures.keuze_opdracht.FollowLineUntilDropSpotModule;
import behaviour.modules.procedures.keuze_opdracht.GrabCubeModule;
import behaviour.modules.procedures.keuze_opdracht.KeuzeOpdrachtModule;
import behaviour.modules.procedures.keuze_opdracht.RoamingModule;
import behaviour.modules.procedures.parcour.ParcoursModuleRGB;
import behaviour.modules.procedures.parcour.ParcoursModuleRgbCalibrate;
import behaviour.modules.procedures.testing.ColorRecognizerTestModule;
import behaviour.modules.procedures.testing.ColorSensorTesterModule;
import behaviour.modules.procedures.testing.LijnenTester;
import behaviour.modules.procedures.testing.RegenBoogChecker;
import behaviour.modules.procedures.testing.RodeLijnTester;
import lejos.ev3.tools.EV3Control;
import lejos.hardware.Brick;
import lejos.hardware.Key;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.factories.MainModuleFactory;
import nl.hva.miw.robot.cohort13.functionality.ClosestColorFinder;
import nl.hva.miw.robot.cohort13.functionality.ColorSensorControl;
import nl.hva.miw.robot.cohort13.functionality.KeyInputControl;
import nl.hva.miw.robot.cohort13.functionality.MemoryOpdracht2;
import nl.hva.miw.robot.cohort13.functionality.MotorControl;
import nl.hva.miw.robot.cohort13.functionality.ProximityControl;
import nl.hva.miw.robot.cohort13.resources.Colors;

//Dit is de presentatie versie


/*
* De main class.
* Calls all the other classes.
* Initializes sensorports/motorports
* Menu initialization on brick
*
*/
public class Marvin {
	private Brick brick;
	private BehaviourModule mainModule;		
	
	private ColorSensorControl colorSensorControlDown;
	private ColorSensorControl colorSensorControlFront;
	public EV3TouchSensor touchSensor;
	private MotorControl motorControl;
	private KeyInputControl keyInputManager;
	private ProximityControl proximityControl;
	private ClosestColorFinder closestColorFinder;
	
	private MemoryOpdracht2 memoryOpdracht2;
	
	public MarvinState state;
	
	public Marvin() {	
		brick = LocalEV3.get(); 
		
		this.colorSensorControlFront = new ColorSensorControl(this, SensorPort.S3);
		this.colorSensorControlDown = new ColorSensorControl(this, SensorPort.S4);
		this.touchSensor = new EV3TouchSensor(SensorPort.S2);
		this.motorControl = new MotorControl(this);
		this.keyInputManager = new KeyInputControl(this);
		this.proximityControl = new ProximityControl(this);
		this.mainModule = new MainModuleFactory().createModule(this);	
		this.closestColorFinder = new ClosestColorFinder(this);
		
		this.memoryOpdracht2 = new MemoryOpdracht2(this);
	}
	
	public Brick getBrick() {
		return brick;
	}
	
	public ColorSensorControl getColorSensorControlDown() {
		return colorSensorControlDown;
	}
	
	public ColorSensorControl getColorSensorControlFront() {
		return colorSensorControlFront;
	}
	
	public MotorControl getMotorControl() {
		return motorControl;
	}
	
	public KeyInputControl getKeyInputManager() {
		return keyInputManager;
	}
	
	public ProximityControl getProximityControl() {
		return proximityControl;
	}
	
	public ClosestColorFinder getClosestColorFinder() {
		return closestColorFinder;
	}
	
	public MemoryOpdracht2 getMemoryOpdracht2() {
		return memoryOpdracht2;
	}
	
	public void incrementState(int amount) {
		// Hoeveel programma's er worden gedraaid.
		int stateSize = MarvinState.getAmountOfStates();		
		int stateNumber = (state.stateNumber + amount) % stateSize;	
		//System.out.println("stateNumber: " + stateNumber);
		state = MarvinState.getStateByNumber(stateNumber); 
	}
	
	public void run() {		
		mainModule.execute();
	}
	
	public boolean keyPressed(Key key) {
		if (key.isDown()) {
			while (key.isDown()) {}
			return true;
		}
		return false;

	}
	
	public static void main(String[] args) {
		Marvin marvin = new Marvin(); 	//instantieert Marvin
		marvin.run(); 					//voert de onderstaande void uit
	}
}
