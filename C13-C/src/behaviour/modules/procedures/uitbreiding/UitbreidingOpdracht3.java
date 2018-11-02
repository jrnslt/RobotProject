package behaviour.modules.procedures.uitbreiding;

import nl.hva.miw.robot.cohort13.Marvin;
import java.util.ArrayList;
import behaviour.modules.BehaviourModule;
import behaviour.modules.procedures.keuze_opdracht.DropCubeModule;
import behaviour.modules.procedures.keuze_opdracht.GrabCubeModule;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.ColorSensorControl;
import nl.hva.miw.robot.cohort13.functionality.KeyInputControl;
import nl.hva.miw.robot.cohort13.functionality.MotorControl;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class UitbreidingOpdracht3 extends behaviour.modules.BehaviourModule {

	ColorSensorControl colorSensorControlDown;
	EV3ColorSensor colorSensorDown;
	ColorSensorControl colorSensorControlFront;
	EV3ColorSensor colorSensorFront;
	boolean sameColor;
	MotorControl motorControl;
	GrabCubeModule grabCube;
	DropCubeModule dropCube;
	KeyInputControl keyInputManager;
	
	public UitbreidingOpdracht3(Marvin marvin) {
		super(marvin);
		this.colorSensorControlDown = getMarvin().getColorSensorControlDown();
		this.colorSensorDown = colorSensorControlDown.getColorSensor();
		this.colorSensorControlFront = getMarvin().getColorSensorControlFront();
		this.colorSensorFront = colorSensorControlFront.getColorSensor();
		this.sameColor = false;
		this.motorControl = getMarvin().getMotorControl();
		this.keyInputManager = 	getMarvin().getKeyInputManager();

	}

	// execute roept de verschillende methodes aan
	@Override
	public boolean execute() {
		//onderste sensor calibreren
		colorSensorControlDown.calibrateSensor();
		Delay.msDelay(3000);
		//wachten tot de knop ingedrukt wordt
		keyInputManager.waitForKey(Button.ENTER);
		Delay.msDelay(2000);
	
		//voorste sensor calibreren 
		colorSensorControlFront.calibrateSensor();
		Delay.msDelay(3000);
		//wachten tot de knop ingedrukt wordt
		keyInputManager.waitForKey(Button.ENTER);
		Delay.msDelay(2000);
		
		opdracht3();
		keyInputManager.waitForKey(Button.ENTER);
		Sound.beep();
	
		opdracht3();
		keyInputManager.waitForKey(Button.ENTER);

		Sound.beep();
		opdracht3();
		keyInputManager.waitForKey(Button.ENTER);

		Sound.beep();

		return true;
	}

	public boolean opdracht3() {
		ArrayList<MColor> colors = new ArrayList<>();
		colors.add(Colors.RED);
		colors.add(Colors.YELLOW);
		colors.add(Colors.CHARTREUSE);
		colors.add(Colors.GREEN);
		colors.add(Colors.CYAN);
		colors.add(Colors.DARK_BLUE);
		colors.add(Colors.LIGHT_BLUE);
		colors.add(Colors.LIGHT_PURPLE);
		colors.add(Colors.PINK);
		colors.add(Colors.WHITE);
		colors.add(Colors.BLACK);

		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);

		// hij moet ook kunnen grijpen en loslatn

		grabCube = new GrabCubeModule(getMarvin());
		dropCube = new DropCubeModule(getMarvin());

		// kleur van kubus moet worden gemeten en opgeslagen
		MColor cubeColor = getColorofCube();
		Delay.msDelay(1000);
		//de kleur wordt op het scherm geprint
		textLCD.drawString("" + cubeColor.getColorName(), 1, 6);
		Delay.msDelay(3000);

		// een boolean voor has cube
		boolean hasCube = false;
		// grijpertje openen
		motorControl.letLoose(200, 1500);
		Delay.msDelay(300);
		// motortje stoppen
		motorControl.stop();
		Delay.msDelay(500);
		// blokje grijpen

		motorControl.grabIt();
		motorControl.stop();
		
		hasCube = true;

		// benedensensor
		SensorMode sensorModeRGBDown = colorSensorDown.getRGBMode();
		colorSensorDown.setFloodlight(Color.WHITE);
		colorSensorDown.setCurrentMode(sensorModeRGBDown.getName());

		// benedensensor wordt gesampeld
		float[] sampleRGB = new float[sensorModeRGBDown.sampleSize()];
		// getMarvin().getMotorControl().drive(200, 200); //Start drive

		// hascube is true, hij kan gaan rijden
		while (hasCube) {

			// Start drive
			motorControl.drive(200, 200);

			textLCD.setAutoRefresh(false);
			textLCD.refresh();
			textLCD.clear();

			// kleur meten van parcours
			sensorModeRGBDown.fetchSample(sampleRGB, 0);
			float r = sampleRGB[0]; // rood
			float g = sampleRGB[1]; // groen
			float b = sampleRGB[2]; // blauw
			float nr = colorSensorControlDown.getRed(r);
			float ng = colorSensorControlDown.getGreen(g);
			float nb = colorSensorControlDown.getBlue(b);

			MColor closestColor = getMarvin().getClosestColorFinder().getClosestColor(colors,
					new MColor("", nr, ng, nb));

			textLCD.drawString(closestColor.getColorName(), 1, 0);
			textLCD.drawString(cubeColor.getColorName(), 1, 1);
			textLCD.drawString("" + nr, 1, 2);
			textLCD.drawString("" + ng, 1, 3);
			textLCD.drawString("" + nb, 1, 4);

			if (closestColor == Colors.WHITE) {
				motorControl.drive(300, -150);// Go More Right

				Delay.msDelay(100);
			} else if (closestColor == Colors.BLACK || closestColor == Colors.DARK_BLUE) {
				motorControl.drive(-150, 300);// Go More left
				Delay.msDelay(100);

			} else if (closestColor == Colors.GREY) {
				motorControl.drive(200, 200); // Drive forward
				Delay.msDelay(100);
			}

			else if (closestColor == cubeColor) {
				motorControl.stop();
				Delay.msDelay(100);

				motorControl.rotate90Right();
				Delay.msDelay(1000);
				motorControl.letLoose();
				Delay.msDelay(1000);
				motorControl.rotate90Left();
				Delay.msDelay(1000);
				break;
			}
		}

		//de weg wordt vervolgd
		long timeStart = System.currentTimeMillis();

		while (hasCube) {
			getMarvin().getMotorControl().drive(200, 200); // Start drive

			textLCD.setAutoRefresh(false);
			textLCD.refresh();
			textLCD.clear();

			sensorModeRGBDown.fetchSample(sampleRGB, 0);
			float r = sampleRGB[0]; // rood
			float g = sampleRGB[1]; // groen
			float b = sampleRGB[2]; // blauw
			float nr = colorSensorControlDown.getRed(r);
			float ng = colorSensorControlDown.getGreen(g);
			float nb = colorSensorControlDown.getBlue(b);

			MColor closestColor = getMarvin().getClosestColorFinder().getClosestColor(colors,
					new MColor("", nr, ng, nb));

			textLCD.drawString(closestColor.getColorName(), 1, 0);
			textLCD.drawString(cubeColor.getColorName(), 1, 1);


			if (closestColor == Colors.WHITE) {
				motorControl.drive(300, -150);// Go More Right

				Delay.msDelay(100);
			} else if (closestColor == Colors.BLACK || closestColor == Colors.DARK_BLUE) {
				motorControl.drive(-150, 300);// Go More left
				Delay.msDelay(100);

			} else if (closestColor == Colors.GREY) {
				motorControl.drive(200, 200); // Drive forward
				Delay.msDelay(100);
			}

			//als tijd om is, stopt de robot.
			if (System.currentTimeMillis() - timeStart > 10000) {
				break;
			}
		}

		motorControl.stop();

		return true;
	}
	
	//methode om de kleur van het blokje te herkennen
	public MColor getColorofCube() {
		SensorMode sensorModeRGBfront = colorSensorFront.getRGBMode();
		colorSensorFront.setFloodlight(Color.WHITE);
		colorSensorFront.setCurrentMode(sensorModeRGBfront.getName());

		ArrayList<MColor> colors = new ArrayList<>();
		colors.add(Colors.RED);
		colors.add(Colors.GREEN);
		colors.add(Colors.LIGHT_BLUE);

		float[] sampleRGBfront = new float[sensorModeRGBfront.sampleSize()];
		colorSensorFront.fetchSample(sampleRGBfront, 0);
		float fR = sampleRGBfront[0]; // rood van voorkant
		float fG = sampleRGBfront[1]; // groen van voorkant
		float fB = sampleRGBfront[2]; // blauw van voorkant

		fR = colorSensorControlFront.getRed(fR);
		fG = colorSensorControlFront.getGreen(fG);
		fB = colorSensorControlFront.getBlue(fB);

		MColor closestColorFront = getMarvin().getClosestColorFinder().getClosestColor(colors,
				new MColor("", fR, fG, fB));

		return closestColorFront;
	}

}