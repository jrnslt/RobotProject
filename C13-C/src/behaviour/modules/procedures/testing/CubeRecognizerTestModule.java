package behaviour.modules.procedures.testing;

import behaviour.modules.BehaviourModule;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.functionality.CubeFinder;
import nl.hva.miw.robot.cohort13.functionality.MColor;

public class CubeRecognizerTestModule extends BehaviourModule {
	private EV3ColorSensor colorSensor;
	
	public CubeRecognizerTestModule(Marvin marvin, EV3ColorSensor colorSensor, String testModuleName) {
		super(marvin);
		this.colorSensor = colorSensor;
	}

	@Override
	public boolean execute() {
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		int testCount = 0;
			
	    textLCD.setAutoRefresh(false);
	    
	    SensorMode sensorModeRGB = colorSensor.getRGBMode();   
		colorSensor.setFloodlight(Color.WHITE);
		colorSensor.setCurrentMode(sensorModeRGB.getName());		
		
	    while (true) {
	        textLCD.setAutoRefresh(false);
	        textLCD.refresh();
	        textLCD.clear();

	        CubeFinder cubeFinder = getMarvin().getCubeFinder();
	        
	        MColor color = cubeFinder.getCube(colorSensor);
	        MColor seenColor = cubeFinder.getSeenColor(colorSensor);
	        
	        String cube = "";
	        
	        if (color != null) {
	        	cube = color.getColorName() + " cube";
	        } else {
	        	cube = "nothing";
	        }
	  
		    int distanceValue = getMarvin().getProximityManager().getDistance();

		    textLCD.drawString("Cube: " + testCount, 1, 1);
		    textLCD.drawString("" + cube, 1, 2);
		    if (color != null) {
			    textLCD.drawString(""  + ", " +  color.getRed(), 1, 3);
			    textLCD.drawString(""  + ", " +  color.getGreen(), 1, 4);
			    textLCD.drawString(""  + ", " +  color.getBlue(), 1, 5);
		    }
		    textLCD.drawString(""  + "" +  distanceValue, 1, 6);
  
        	testCount++;
        	
        	Delay.msDelay(250);
	        
	        if (testCount > 150) {
	        	break;
	        }
	    }  

	    return true;
	}

}
