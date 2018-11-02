package behaviour.modules.procedures.parcour;

import java.util.ArrayList;

import behaviour.modules.BehaviourModule;
import behaviour.modules.procedures.keuze_opdracht.GroundColorReaderModule;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class ParkourZwart extends GroundColorReaderModule {
	private MColor color;

	public ParkourZwart(Marvin marvin) {
		super(marvin, "", null, "");
	}

	@Override
	public boolean execute() {
		
		getMarvin().getColorSensorControlDown().calibrateSensor();

		ArrayList<MColor> colors = new ArrayList<>();
		colors.add(Colors.RED);
		colors.add(Colors.BLUE);
		colors.add(Colors.WHITE);
		colors.add(Colors.BLACK);
		
		while (true) {
			MColor closestColor = readColor(colors);
		
			if (closestColor != Colors.BLACK) {	
				while (readColor(colors) != Colors.BLACK) {
					
					if (readColor(colors) == Colors.RED) {
						getMarvin().getMotorControl().drive(150, 150); // rechts
						Delay.msDelay(100);	
					} else {
						getMarvin().getMotorControl().drive(150, -150); // rechts
						Delay.msDelay(100);	
						getMarvin().getMotorControl().drive(150, 150); // rechts
						Delay.msDelay(100);	
					} 
					
				}	
			} else if (closestColor == Colors.BLACK) { // links
						
				while (readColor(colors) == Colors.BLACK) {
					getMarvin().getMotorControl().drive(-150, 150);
					Delay.msDelay(400);	
				}
			}
		}
	}
}
