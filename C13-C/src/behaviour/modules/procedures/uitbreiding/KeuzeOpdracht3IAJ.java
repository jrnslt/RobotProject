package behaviour.modules.procedures.uitbreiding;

import nl.hva.miw.robot.cohort13.Marvin;
import java.util.ArrayList;
import behaviour.modules.procedures.keuze_opdracht.DropCubeModule;
import behaviour.modules.procedures.keuze_opdracht.GrabCubeModule;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.functionality.ColorSensorControl;
import nl.hva.miw.robot.cohort13.functionality.MotorControl;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class KeuzeOpdracht3IAJ extends behaviour.modules.BehaviourModule {

	private final long runTime = 1000000;
	private final double fairlyBlack = 0.4;
	private final double regularBlack = 0.8;
	private final double blackWhite = 1.2;
	private final double regularWhite = 1.6;
	private final double extremeWhite = 1.77;
	private final double reallyBlue = 2.5;
	
	ColorSensorControl colorSensorControlDown;
    EV3ColorSensor colorSensorDown;
    ColorSensorControl colorSensorControlFront;
    EV3ColorSensor colorSensorFront;
    boolean sameColor;
    MotorControl motorControl;
    GrabCubeModule grabCube;
    DropCubeModule dropCube;
    
	public KeuzeOpdracht3IAJ(Marvin marvin) {
		super(marvin);
		this.colorSensorControlDown = getMarvin().getColorSensorControlDown();
        this.colorSensorDown = colorSensorControlDown.getColorSensor();
        this.colorSensorControlFront = getMarvin().getColorSensorControlFront();
        this.colorSensorFront = colorSensorControlFront.getColorSensor();
        this.sameColor = false;
        this.motorControl = getMarvin().getMotorControl();
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
		
        
        //hij moet ook kunnen grijpen en loslatn

      		grabCube = new GrabCubeModule(getMarvin());
      		dropCube = new DropCubeModule(getMarvin());
    		
        //kleur van kubus moet worden gemeten
      	MColor cubeColor = getColorofCube();
      	Delay.msDelay(1000);
		textLCD.drawString("" + cubeColor.getColorName(), 1, 6);
      	Delay.msDelay(3000);

      	//een boolean voor has cube
      		boolean hasCube = false;
      		//grijpertje openen
      		getMarvin().getMotorControl().letLoose(200, 1500);
      		Delay.msDelay(300);
      		//motortje stoppen
      		getMarvin().getMotorControl().stop();
      		Delay.msDelay(500);
      		//blokje grijpen
      		
          	getMarvin().getMotorControl().grabIt();
          	getMarvin().getMotorControl().stop();
          	hasCube = true;

      	
      	// benedensensor
    	SensorMode sensorModeRGBDown = colorSensorDown.getRGBMode();
		colorSensorDown.setFloodlight(Color.WHITE);
		colorSensorDown.setCurrentMode(sensorModeRGBDown.getName());

		// benedensensor wordt gesampeld
		float[] sampleRGB = new float[sensorModeRGBDown.sampleSize()];

		while (hasCube) {	
			getMarvin().getMotorControl().drive(200, 200);	//Start drive

	
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

	        MColor closestColor = getMarvin().getClosestColorFinder().
	        getClosestColor(colors, new MColor("", nr, ng, nb));
	    
        	textLCD.drawString(closestColor.getColorName(), 1,0);
        	textLCD.drawString(cubeColor.getColorName(), 1, 1);
        	textLCD.drawString("" + nr, 1, 2);
        	textLCD.drawString("" + ng, 1, 3);
        	textLCD.drawString("" + nb, 1, 4);
        	
      
        	if (closestColor == Colors.WHITE) {
	        	
	        	getMarvin().getMotorControl().drive(300, -150); //Get More Right
				Delay.msDelay(100);
	        } 	else if (closestColor == Colors.BLACK || closestColor == Colors.DARK_BLUE ) {

				getMarvin().getMotorControl().drive(-150, 300); //Get More Left
				Delay.msDelay(100);

	        	
	        } else if (closestColor == Colors.GREY) {
				getMarvin().getMotorControl().drive(200, 200); //Drive forward
				Delay.msDelay(100);

	        }
	          
	        else if (closestColor ==  cubeColor ) {
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
		
		long timeStart = System.currentTimeMillis();
		
		while (hasCube) {
			
			getMarvin().getMotorControl().drive(200, 200);	//Start drive

	
		
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

        MColor closestColor = getMarvin().getClosestColorFinder().
        getClosestColor(colors, new MColor("", nr, ng, nb));
    
    	textLCD.drawString(closestColor.getColorName(), 1,0);
    	textLCD.drawString(cubeColor.getColorName(), 1, 1);
    	
    	long startTime = 0;
    	
    	if (closestColor == Colors.WHITE) {
        	
        	getMarvin().getMotorControl().drive(300, -150); //Get More Right
			Delay.msDelay(100);
        } 	else if (closestColor == Colors.BLACK || closestColor == Colors.BLUE ) {

			getMarvin().getMotorControl().drive(-150, 300); //Get More Left
			Delay.msDelay(100);

        	
        } else if (closestColor == Colors.GREY) {
			getMarvin().getMotorControl().drive(200, 200); //Drive forward
			Delay.msDelay(100);

        }
    	
    	if (System.currentTimeMillis() - startTime > 10000) {
    		break;
    	}
	}
		
		getMarvin().getMotorControl().stop();
		
		return true;
	}

	@Override
	public boolean execute() {
		colorSensorControlDown.calibrateSensor();
		//button press down
		Delay.msDelay(3000);
		colorSensorControlFront.calibrateSensor();
		//button press doen
		Delay.msDelay(3000);
		
		opdracht3();
		getMarvin().getKeyInputManager().waitForKey(Button.ENTER);
		Sound.beep();
		opdracht3();
		getMarvin().getKeyInputManager().waitForKey(Button.ENTER);
		Sound.beep();
		opdracht3();
		getMarvin().getKeyInputManager().waitForKey(Button.ENTER);
		Sound.beep();
		
		return true;
	}
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