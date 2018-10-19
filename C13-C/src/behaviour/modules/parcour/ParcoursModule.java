package behaviour.modules.parcour;


import behaviour.modules.BehaviourModule;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Executable;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.ProximitySensor;
import nl.hva.miw.robot.cohort13.SoundProducer;
import nl.hva.miw.robot.cohort13.Utils;


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
		
		/*
		Runnable runnable = (new Runnable() {
			int count = 0;
			SoundProducer soundProducer = getMarvin().soundProducer;
			ProximitySensor proximitySensor = getMarvin().proximitySensor;
			
			@Override
			public void run() {
		        if (count % 16 == 0) {
					soundProducer.setExecutable(new Executable() {
						float x = 0;
						
						@Override
						public void execute() {
							float[] samples = proximitySensor.getSample();
							
							if (samples != null) {
								
								final int distance = (int)samples[0];
								double sigmoid = Utils.sigmoid(distance / 200f, 5f);
								
								// TODO Auto-generated method stub
							    textLCD.refresh();
						        textLCD.clear();
						        textLCD.drawString("distance:", 2, 1);
						        textLCD.drawString("" + distance, 1, 2);
						        textLCD.drawString("" + sigmoid, 1, 3);
		
						        double invertedSigmoid = 1.0 - sigmoid;
						     
								Sound.playTone(50, 1, (int)(invertedSigmoid * 100));
							}
						}
					});
		        }
		        
		        count ++;
				
			}
			
		});
		
		*/
		
		
		
		long startTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		EV3ColorSensor colorSensor = marvin.colorSensorA;
		SensorMode sensorModeRed = colorSensor.getRedMode();
		colorSensor.setFloodlight(Color.RED);
		colorSensor.setCurrentMode(sensorModeRed.getName());
		float[] sampleRed = new float[sensorModeRed.sampleSize()];
		TextLCD textLCD = marvin.getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
		Sound.beep();
		startDrive();

		while (lastTime - startTime < runTime) {
			lastTime = System.currentTimeMillis();
			sensorModeRed.fetchSample(sampleRed, 0);
			textLCD.refresh();
			textLCD.clear();
			float r = sampleRed[0]; // rood
			//obv lichtIntensiteit wordt de koers bepaald
			
			if (r < fairlyBlack) {
				System.out.printf("Kleur is \nzwart %.3f ", r);
				goMoreLeft();
				Delay.msDelay(100);
			} else if (r < regularBlack) {
				goLeft();

			} else if (r < blackWhite) {
				System.out.printf("Kleur is \n zwart - wit %.3f ", r);
				driveForward();
				Delay.msDelay(100);

			} else if (r < regularWhite) {

				goRight();
				Delay.msDelay(100);

			} else if (r > regularWhite) {
				System.out.printf("Kleur is \n wit %.3f ", r);
				goMoreRight();
				Delay.msDelay(100);
			} else if (r > extremeWhite) {
				rotateLeftCentered();
				Delay.msDelay(100);
			}
		}
		stopRobot();
		return true;	
	}

//	public void startDrive() {
//		marvin.groteMotorLinks.forward();
//		marvin.groteMotorRechts.forward();
//		marvin.groteMotorLinks.setSpeed(200);
//		marvin.groteMotorRechts.setSpeed(200);
//	}
//
//	public void driveForward() {
//		marvin.groteMotorLinks.forward();
//		marvin.groteMotorRechts.forward();
//		marvin.groteMotorLinks.setSpeed(200);
//		marvin.groteMotorRechts.setSpeed(200);
//	}
//
//	public void goLeft() {
//		marvin.groteMotorLinks.forward();
//		marvin.groteMotorRechts.forward();
//		marvin.groteMotorLinks.setSpeed(50);
//		marvin.groteMotorRechts.setSpeed(200);
//	}
//
//	public void goMoreLeft() {
//
//		marvin.groteMotorLinks.backward();
//		marvin.groteMotorLinks.setSpeed(100);
//		marvin.groteMotorRechts.forward();
//		marvin.groteMotorRechts.setSpeed(100);
//
//	}
//
//	public void goRight() {
//		marvin.groteMotorLinks.forward();
//		marvin.groteMotorRechts.backward();
//		marvin.groteMotorLinks.setSpeed(200);
//		marvin.groteMotorRechts.setSpeed(50);
//	}
//
//	public void goMoreRight() {
//
//		marvin.groteMotorLinks.forward();
//		marvin.groteMotorLinks.setSpeed(100);
//		marvin.groteMotorRechts.backward();
//		marvin.groteMotorRechts.setSpeed(100);
//	}
//
//
//	public void stopRobot() {
//		marvin.groteMotorLinks.stop();
//		marvin.groteMotorRechts.stop();
//
//	}
//
//	public void rotateLeftCentered() {
//		marvin.groteMotorLinks.backward();
//		marvin.groteMotorLinks.setSpeed(800);
//		marvin.groteMotorRechts.forward();
//		marvin.groteMotorRechts.setSpeed(800);
//
//	}
	
	
	public void startDrive() {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(300);
		marvin.groteMotorRechts.setSpeed(300);
	}

	public void driveForward() {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(300);
		marvin.groteMotorRechts.setSpeed(300);
	}

	public void goLeft() {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(50);
		marvin.groteMotorRechts.setSpeed(200);
	}

	public void goMoreLeft() {

		marvin.groteMotorLinks.backward();
		marvin.groteMotorLinks.setSpeed(100);
		marvin.groteMotorRechts.forward();
		marvin.groteMotorRechts.setSpeed(100);

	}

	public void goRight() {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.backward();
		marvin.groteMotorLinks.setSpeed(200);
		marvin.groteMotorRechts.setSpeed(50);
	}

	public void goMoreRight() {

		marvin.groteMotorLinks.forward();
		marvin.groteMotorLinks.setSpeed(100);
		marvin.groteMotorRechts.backward();
		marvin.groteMotorRechts.setSpeed(100);
	}


	public void stopRobot() {
		marvin.groteMotorLinks.stop();
		marvin.groteMotorRechts.stop();

	}

	public void rotateLeftCentered() {
		marvin.groteMotorLinks.backward();
		marvin.groteMotorLinks.setSpeed(800);
		marvin.groteMotorRechts.forward();
		marvin.groteMotorRechts.setSpeed(800);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public void waitForKey(Key key) {
		while (key.isUp()) {
			Delay.msDelay(100);
		}
		while (key.isDown()) {
			Delay.msDelay(100);
		}
	}

}