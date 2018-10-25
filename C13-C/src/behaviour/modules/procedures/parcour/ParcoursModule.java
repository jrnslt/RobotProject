package behaviour.modules.procedures.parcour;

import behaviour.modules.BehaviourModule;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.PlayLoopedSound;

/**
 * @author JeroenS Er wordt een parcours aangemaakt die runt voor een bepaalde
 *         tijd "runTime"
 *
 */
public class ParcoursModule extends BehaviourModule {

	private final long runTime = 100000;
	private final double fairlyBlack = 0.10;
	private final double regularBlack = 0.25;
	private final double blackWhite = 0.35;
	private final double regularWhite = 0.50;
	private final double extremeWhite = 0.8;

	// Parcours wordt aangemaakt voor Marvin
	public ParcoursModule(Marvin marvin) {
		super(marvin);
	}

	public boolean execute() {
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		EV3ColorSensor colorSensor = getMarvin().colorSensorA;
		SensorMode sensorModeRgb = colorSensor.getRedMode();
		colorSensor.setFloodlight(Color.RED);
		colorSensor.setCurrentMode(sensorModeRgb.getName());
		float[] sampleRed = new float[sensorModeRgb.sampleSize()];
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
		//Sound.beep();
		
		getMarvin().getMotorControl().drive(300, 300);	//Start Drive

		PlayLoopedSound p = new PlayLoopedSound(100);
		p.start();
		
		while (lastTime - startTime < runTime) {
			lastTime = System.currentTimeMillis();
			sensorModeRgb.fetchSample(sampleRed, 0);
			textLCD.refresh();
			textLCD.clear();
			
			float r = sampleRed[0]; // rood
			
			//obv lichtIntensiteit wordt de koers bepaald
		
			if (r < fairlyBlack) {
				System.out.printf("Kleur is \nzwart %.3f ", r);
				getMarvin().getMotorControl().drive(100, -100); //Go More Left
				Delay.msDelay(100);
			} else if (r < regularBlack) {
				getMarvin().getMotorControl().drive(50, 200);	//Go Left
			} else if (r < blackWhite) {
				System.out.printf("Kleur is \n zwart - wit %.3f ", r);
				getMarvin().getMotorControl().drive(300, 300);	//Drive Forward
				Delay.msDelay(100);
			} else if (r < regularWhite) {
				getMarvin().getMotorControl().drive(200, 50);	//Go Right
				Delay.msDelay(100);
			} else if (r > regularWhite) {
				System.out.printf("Kleur is \n wit %.3f ", r);
				getMarvin().getMotorControl().drive(-100, 100); //Go More Right
				Delay.msDelay(100);
			} else if (r > extremeWhite) {
				//rotateLeftCentered();
				//Delay.msDelay(100);
			}
		}
	
		getMarvin().getMotorControl().stop();
		
		try {
			p.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;	
	}
}