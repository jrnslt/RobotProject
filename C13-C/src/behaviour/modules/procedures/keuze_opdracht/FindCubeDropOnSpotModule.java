package behaviour.modules.procedures.keuze_opdracht;

import java.util.ArrayList;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.ColorSensorControl;
import nl.hva.miw.robot.cohort13.functionality.MotorControl;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class FindCubeDropOnSpotModule extends BehaviourModule {
	ColorSensorControl colorSensorControlDown;
	EV3ColorSensor colorSensorDown = colorSensorControlDown.getColorSensor();
	ColorSensorControl colorSensorControlFront = getMarvin().getColorSensorControlFront();
	EV3ColorSensor colorSensorFront = colorSensorControlFront.getColorSensor();
	boolean sameColor = false;
	MotorControl motorControl = getMarvin().getMotorControl();
	GrabCubeModule grabCube; 
	DropCubeModule dropCube; 
	
	
	public FindCubeDropOnSpotModule(Marvin marvin) {
		super(marvin);
		this.colorSensorControlDown = getMarvin().getColorSensorControlDown();
		this.colorSensorDown = colorSensorControlDown.getColorSensor();
	}

	@Override
	public boolean execute() {
	//	ColorSensorControl colorSensorControlDown = getMarvin().getColorSensorControlDown();
	//	EV3ColorSensor colorSensorDown = colorSensorControlDown.getColorSensor();
	//	ColorSensorControl colorSensorControlFront = getMarvin().getColorSensorControlFront();
	//	EV3ColorSensor colorSensorFront = colorSensorControlFront.getColorSensor();
		colorSensorControlDown.calibrateSensor();
		colorSensorControlFront.calibrateSensor();
		Delay.msDelay(5000);


		// benedensensor
		SensorMode sensorModeRGBdown = colorSensorDown.getRGBMode();
		colorSensorDown.setFloodlight(Color.WHITE);
		colorSensorDown.setCurrentMode(sensorModeRGBdown.getName());

		// voorkant sensor
		SensorMode sensorModeRGBfront = colorSensorFront.getRGBMode();
		colorSensorFront.setFloodlight(Color.WHITE);
		colorSensorFront.setCurrentMode(sensorModeRGBfront.getName());

		// benedensensor wordt gesampeld
		float[] sampleRGB = new float[sensorModeRGBdown.sampleSize()];
		// sample voorkant
		float[] sampleRGBfront = new float[sensorModeRGBfront.sampleSize()];

		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
		Sound.beep(); // dat is gebeurt

		//float distance = measureDistance();

		
		//de kleuren die hij kan herkennen
		
		ArrayList<MColor> colors = new ArrayList<>();
		colors.add(Colors.RED);
		colors.add(Colors.GREEN);
		colors.add(Colors.BLUE_GREY);
		colors.add(Colors.ORANGE);
		colors.add(Colors.WHITE);
		colors.add(Colors.BLACK);
		colors.add(Colors.DARK_BLUE);

		//hij moet ook kunnen grijpen en loslatn
		grabCube = new GrabCubeModule(getMarvin());
		dropCube = new DropCubeModule(getMarvin());
		
		//kleur van kubus moet worden gemeten
		
		MColor cubeColor = getColorofCube();
		
		
		//een boolean voor has cube
		boolean hasCube = false;
		//grijpertje openen
		getMarvin().getMotorControl().letLoose(200, 1500);
		Delay.msDelay(300);
		//motortje stoppen
		getMarvin().getMotorControl().stop();
		Delay.msDelay(500);
		//blokje grijpen
    	getMarvin().getMotorControl().grabItForward(200,1700);
    	getMarvin().getMotorControl().stop();
    	hasCube = true;
		
    	//hij heeft een kubus, dus kan hij gaan rijden
		
		while (hasCube) {
			sensorModeRGBdown.fetchSample(sampleRGB, 0);
		//	sensorModeRGBfront.fetchSample(sampleRGBfront, 0);

			textLCD.setAutoRefresh(false);
			textLCD.refresh();
			textLCD.clear();

			float r = sampleRGB[0]; // rood
			float g = sampleRGB[1]; // groen
			float b = sampleRGB[2]; // blauw

			r = colorSensorControlDown.getRed(r);
			g = colorSensorControlDown.getGreen(g);
			b = colorSensorControlDown.getBlue(b);

			MColor closestColorDown = getMarvin().getClosestColorFinder().getClosestColor(colors,
					new MColor("", r, g, b));
			textLCD.drawString("" + closestColorDown.getColorName(), 1, 6);

			textLCD.drawString("RGB mode", 1, 1);
			textLCD.drawString("" + r, 1, 2);
			textLCD.drawString("" + g, 1, 3);
			textLCD.drawString("" + b, 1, 4);
			textLCD.drawString(closestColorDown.getColorName(), 1, 5);



			
			
			
			if (closestColorDown == Colors.BLACK) {
				//als hij zwart ziet, moet hij bijsturen;
			
				textLCD.drawString("black 123", 1, 5);
	        	getMarvin().getMotorControl().drive(-150, 150);
	        	Delay.msDelay(400);
	        	getMarvin().getMotorControl().drive(200, 200);
				Delay.msDelay(400);
				
			} else if (closestColorDown ==  Colors.WHITE) {
				getMarvin().getMotorControl().drive(150, 150);
	        	Delay.msDelay(400);

			} else if (closestColorDown == Colors.DARK_BLUE) {
				getMarvin().getMotorControl().rotate90Left();	
				
			}			
//			
//			else if (closestColorDown !=  Colors.BLACK && closestColorDown != Colors.WHITE  ) {
//				getMarvin().getMotorControl().drive(150, 150);
//	        	Delay.msDelay(400);
//				
//			}		
//			else {
//	        	getMarvin().getMotorControl().drive(150, 150);
//	        	Delay.msDelay(400);
			//MColor cubeColor = getColorofCube();
			//checken of hij al de 
			sameColor = findColorField(cubeColor);
				
				while (!sameColor) {
				motorControl.driveForward(200, 200);
				Delay.msDelay(300);
				sameColor = findColorField(cubeColor);
				

			} // kleur checken

				if (sameColor) {
					motorControl.stop();
					Delay.msDelay(300);
					Sound.buzz();
					Delay.msDelay(300);
					dropCube.execute();
					
				}
				
		}

//		
//		float[] afstanden = new float[10000];
//		int index = 0;
//	 = 1000;
// 
//		while (distance > 500) {
//			makeTurn();
//		Delay.msDelay(150);
//		afstanden[index] = measureDistance();
//		index++;

//		if (index == 9999) {
//			index = 0;
//		}
//		
//		if (afstanden[index] < afstanden[index-1]) {
//			motorControl.driveForward(100, 100);
//    		Delay.msDelay(150);
//    		afstanden[index] = measureDistance();
//    		index++;
//
//			if (afstanden[index] < 100) {
//				Sound.beepSequenceUp();
//	    		Delay.msDelay(150);
//				break;
//			}
//		}

//		boolean objectFound = false;
//
//		
//		while (!objectFound) {
//		
//			distance = measureDistance();
//			if (distance < 250) {
//				motorControl.driveForward(100, 100);
//	    		Delay.msDelay(150);
//				distance = measureDistance();
//				Sound.beep();
//				
//				if (distance < 30) {
//					objectFound = true;
//					motorControl.stop();
//					
//				} 
//			}
//		}

		// TODO Auto-generated method stub
		return true;
	}

	public void makeTurn() {
		motorControl.driveForward(-100, 100);
		Delay.msDelay(150);

	}

	public float measureDistance() {
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		final int HALF_SECOND = 500;

//	long startTime = System.currentTimeMillis();
//	long currentTime = System.currentTimeMillis();
//	
//	while(currentTime - startTime < 10000) {
//		currentTime = System.currentTimeMillis();

		int distanceValue = getMarvin().getProximityManager().getDistance();

		textLCD.refresh();
		textLCD.clear();
		textLCD.drawString("Distance:", 2, 1);
		textLCD.drawString("" + distanceValue, 1, 2);
		Delay.msDelay(HALF_SECOND);
//	}

		return distanceValue;
	}

	public boolean findColorField(MColor closestColorFront) {
		SensorMode sensorModeRGBdown = colorSensorDown.getRGBMode();
		colorSensorDown.setFloodlight(Color.WHITE);
		colorSensorDown.setCurrentMode(sensorModeRGBdown.getName());

		float[] sampleRGB = new float[sensorModeRGBdown.sampleSize()];

		float r = sampleRGB[0]; // rood
		float g = sampleRGB[1]; // groen
		float b = sampleRGB[2]; // blauw

		r = colorSensorControlDown.getRed(r);
		g = colorSensorControlDown.getGreen(g);
		b = colorSensorControlDown.getBlue(b);

		ArrayList<MColor> colors = new ArrayList<>();
		colors.add(Colors.RED);
		colors.add(Colors.GREEN);
		colors.add(Colors.BLUE);
//		colors.add(Colors.BLUE_GREY);
//		colors.add(Colors.ORANGE);
//		colors.add(Colors.WHITE);
//		colors.add(Colors.BLACK);

		MColor closestColorDown = getMarvin().getClosestColorFinder().getClosestColor(colors, new MColor("", r, g, b));

		if (closestColorDown == closestColorFront) {
			return true;
		}

		return false;
	}
	
	//deze methode checkt de kleur van het blokje dat wordt aangeboden;
	
	public MColor getColorofCube() {
		SensorMode sensorModeRGBfront = colorSensorFront.getRGBMode();
		colorSensorFront.setFloodlight(Color.WHITE);
		colorSensorFront.setCurrentMode(sensorModeRGBfront.getName());
		
		ArrayList<MColor> colors = new ArrayList<>();
		colors.add(Colors.RED);
		colors.add(Colors.GREEN);
		colors.add(Colors.BLUE);
		
		
		float[] sampleRGBfront = new float[sensorModeRGBfront.sampleSize()];
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
