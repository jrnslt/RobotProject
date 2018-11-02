package nl.hva.miw.robot.cohort13.factories;

import behaviour.modules.BehaviourModule;
import behaviour.modules.general.Rotate45DegreesCounterClockwiseModule;
import behaviour.modules.general.SetBrickLedPatternModule;
import behaviour.modules.general.SetColorModeRGB;
import behaviour.modules.general.SetColorSensorFloodLight;
import behaviour.modules.logic.InverterModule;
import behaviour.modules.logic.LeafModule;
import behaviour.modules.logic.LoopModule;
import behaviour.modules.logic.SequenceModule;
import behaviour.modules.logic.SequenceUntilFailModule;
import behaviour.modules.logic.SucceederModule;
import behaviour.modules.procedures.keuze_opdracht.ConditionCubeDroppedModule;
import behaviour.modules.procedures.keuze_opdracht.ConditionCubeFoundModule;
import behaviour.modules.procedures.keuze_opdracht.ConditionRoaming;
import behaviour.modules.procedures.keuze_opdracht.Condition_CurrentColor;
import behaviour.modules.procedures.keuze_opdracht.DriveForwardModule;
import behaviour.modules.procedures.keuze_opdracht.DropCubeModule;
import behaviour.modules.procedures.keuze_opdracht.FollowLineForAShortWhile;
import behaviour.modules.procedures.keuze_opdracht.FollowLineUntilCubeIsFoundModule;
import behaviour.modules.procedures.keuze_opdracht.FollowLineUntilDropSpotModule;
import behaviour.modules.procedures.keuze_opdracht.GrabCubeModule;
import behaviour.modules.procedures.keuze_opdracht.OpenArmModule;
import behaviour.modules.procedures.keuze_opdracht.ResetVariablesOpdracht2Module;
import behaviour.modules.procedures.keuze_opdracht.RijdenNaarMiddenOpdracht2Module;
import behaviour.modules.procedures.keuze_opdracht.RoamingModule;
import behaviour.modules.procedures.keuze_opdracht.SetVariableCubeDroppedModule;
import behaviour.modules.procedures.keuze_opdracht.SetVariableCubeFound;
import behaviour.modules.procedures.keuze_opdracht.SetVariableRoaming;
import behaviour.modules.procedures.keuze_opdracht.VictoryDanceOpdracht2Module;
import lejos.robotics.Color;
import nl.hva.miw.robot.cohort13.MColor;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class Opdracht2ModuleFactory extends ModuleFactory {

	@Override
	public BehaviourModule createModule(Marvin marvin) {

		LeafModule leafModule = new LeafModule(marvin);
			SequenceModule sequenceModule = new SequenceModule(marvin);
				SetColorModeRGB setColorModeRGBModule = new SetColorModeRGB(marvin);
				ResetVariablesOpdracht2Module resetVariablesOpdracht2Module = new ResetVariablesOpdracht2Module(marvin);
				OpenArmModule openArmmodule = new OpenArmModule(marvin);
				LoopModule loopModule = new LoopModule(marvin);
					SequenceUntilFailModule sequenceUntilFailModuleA = new SequenceUntilFailModule(marvin);
						//Roaming
						SucceederModule succeederModule0 = new SucceederModule(marvin);
							SequenceModule sequenceModule0 = new SequenceModule(marvin);
								ConditionRoaming conditionRoaming0 = new ConditionRoaming(marvin, true);
								SetBrickLedPatternModule setLedPatternModule0 = new SetBrickLedPatternModule(marvin, 9);
								SetColorSensorFloodLight setColorSensorFloodLight0 = new SetColorSensorFloodLight(marvin, Color.WHITE);
								RoamingModule roamingModule0 = new RoamingModule(marvin);
								SetVariableRoaming setVariableRoaming0 = new SetVariableRoaming(marvin, false);
								
						//Colors
						SucceederModule succeederModuleSA1 = createCubeModuleSequence(marvin, Colors.TAPE_RED, Color.RED, "rood");
						SucceederModule succeederModuleSA2 = createCubeModuleSequence(marvin, Colors.TAPE_GREEN, Color.WHITE, "groen");
						SucceederModule succeederModuleSA3 = createCubeModuleSequence(marvin, Colors.TAPE_BLUE, Color.BLUE, "blue");
						
						//Check
						InverterModule inverterModule4 = new InverterModule(marvin);	
							SequenceUntilFailModule sequenceUntilFailModule4 = new SequenceUntilFailModule(marvin);
								ConditionCubeDroppedModule conditionRedCubeDroppedModule4 = new ConditionCubeDroppedModule(marvin, true, Colors.TAPE_RED);
								ConditionCubeDroppedModule conditionGreenCubeDroppedModule4 = new ConditionCubeDroppedModule(marvin, true, Colors.TAPE_GREEN);				
								ConditionCubeDroppedModule conditionBlueCubeDroppedModule4 = new ConditionCubeDroppedModule(marvin, true, Colors.TAPE_BLUE);							
				//Einde		
				RijdenNaarMiddenOpdracht2Module rijdenNaarMiddenOpdracht2Module = new RijdenNaarMiddenOpdracht2Module(marvin);	
				VictoryDanceOpdracht2Module victoryDanceOpdracht2Module = new VictoryDanceOpdracht2Module(marvin);
			
		
		leafModule.addModule(sequenceModule);
			sequenceModule.addModule(setColorModeRGBModule);
			sequenceModule.addModule(resetVariablesOpdracht2Module);
			sequenceModule.addModule(openArmmodule);
			sequenceModule.addModule(loopModule);	
				loopModule.addModule(sequenceUntilFailModuleA);
					//Roaming
					sequenceUntilFailModuleA.addModule(succeederModule0);
						succeederModule0.addModule(sequenceModule0);
							sequenceModule0.addModule(conditionRoaming0);
							sequenceModule0.addModule(setLedPatternModule0);
							sequenceModule0.addModule(setColorSensorFloodLight0);
							sequenceModule0.addModule(roamingModule0);
							sequenceModule0.addModule(setVariableRoaming0);	

					sequenceUntilFailModuleA.addModule(succeederModuleSA1);
					sequenceUntilFailModuleA.addModule(succeederModuleSA2);
					sequenceUntilFailModuleA.addModule(succeederModuleSA3);

					//Check
					sequenceUntilFailModuleA.addModule(inverterModule4);
						inverterModule4.addModule(sequenceUntilFailModule4);
							sequenceUntilFailModule4.addModule(conditionRedCubeDroppedModule4);
							sequenceUntilFailModule4.addModule(conditionGreenCubeDroppedModule4);
							sequenceUntilFailModule4.addModule(conditionBlueCubeDroppedModule4);
			sequenceModule.addModule(rijdenNaarMiddenOpdracht2Module);		
			sequenceModule.addModule(victoryDanceOpdracht2Module);			
								
								
		return leafModule;
	}
	
	private SucceederModule createCubeModuleSequence(Marvin marvin, MColor color, int floodLightColor, String n) {
		//Red
		SucceederModule succeederModuleSA1 = new SucceederModule(marvin);
			SequenceUntilFailModule sequenceUntilFailModuleA1 = new SequenceUntilFailModule(marvin);
				ConditionRoaming conditionRoaming1 = new ConditionRoaming(marvin, false);
				Condition_CurrentColor conditionCurrentColor = new Condition_CurrentColor(marvin, color);
				SequenceUntilFailModule sequenceUntilFailModuleB1 = new SequenceUntilFailModule(marvin);
					ConditionCubeFoundModule conditionCubeFoundModuleA1 = new ConditionCubeFoundModule(marvin, false, color);
					SetBrickLedPatternModule ledPatternModule = new SetBrickLedPatternModule(marvin, 0);
					SetColorSensorFloodLight setColorSensorFloodLight1 = new SetColorSensorFloodLight(marvin, floodLightColor);
					FollowLineUntilCubeIsFoundModule followLineUntilDropSpotModuleA1 = new FollowLineUntilCubeIsFoundModule(marvin, color, n);
					GrabCubeModule grabCubeModule1 = new GrabCubeModule(marvin);
					Rotate45DegreesCounterClockwiseModule rotate45DegreesCounterClockwiseA1 = new Rotate45DegreesCounterClockwiseModule(marvin);
					DriveForwardModule driveForwardModuleA1 = new DriveForwardModule(marvin);
					SetVariableCubeFound setVariableRedCubeFound1 = new SetVariableCubeFound(marvin, true, color);		
				SequenceUntilFailModule sequenceUntilFailModuleC1 = new SequenceUntilFailModule(marvin);
					//ConditionCubeFoundModule conditionCubeFoundModuleB1 = new ConditionCubeFoundModule(marvin, true, color);
					ConditionCubeDroppedModule conditionCubeDroppedModuleB1 = new ConditionCubeDroppedModule(marvin, false, color);
					FollowLineUntilDropSpotModule followLineUntilDropSpot1 = new FollowLineUntilDropSpotModule(marvin, color);
					DropCubeModule dropCubeModuleA1 = new DropCubeModule(marvin);
					Rotate45DegreesCounterClockwiseModule rotate45DegreesCounterClockwiseB1 = new Rotate45DegreesCounterClockwiseModule(marvin);
					SetVariableCubeDroppedModule setVariableCubeDroppedModule1 = new SetVariableCubeDroppedModule(marvin, true, color);
					FollowLineForAShortWhile followLineForAShortWhile1 = new FollowLineForAShortWhile(marvin, color);	
					SetVariableRoaming setVariableRoaming1 = new SetVariableRoaming(marvin, true);
		
		succeederModuleSA1.addModule(sequenceUntilFailModuleA1);
			sequenceUntilFailModuleA1.addModule(conditionCurrentColor);
			sequenceUntilFailModuleA1.addModule(conditionRoaming1);
			sequenceUntilFailModuleA1.addModule(sequenceUntilFailModuleB1);
				sequenceUntilFailModuleB1.addModule(conditionCubeFoundModuleA1);
				sequenceUntilFailModuleB1.addModule(ledPatternModule);
				sequenceUntilFailModuleB1.addModule(setColorSensorFloodLight1);
				sequenceUntilFailModuleB1.addModule(followLineUntilDropSpotModuleA1);	
				sequenceUntilFailModuleB1.addModule(grabCubeModule1);	
				sequenceUntilFailModuleB1.addModule(rotate45DegreesCounterClockwiseA1);
				sequenceUntilFailModuleB1.addModule(driveForwardModuleA1);
				sequenceUntilFailModuleB1.addModule(setVariableRedCubeFound1);	
			sequenceUntilFailModuleA1.addModule(sequenceUntilFailModuleC1);			
				//sequenceUntilFailModuleC1.addModule(conditionCubeFoundModuleB1);
				sequenceUntilFailModuleC1.addModule(conditionCubeDroppedModuleB1);
				sequenceUntilFailModuleC1.addModule(followLineUntilDropSpot1);
				sequenceUntilFailModuleC1.addModule(dropCubeModuleA1);
				sequenceUntilFailModuleC1.addModule(rotate45DegreesCounterClockwiseB1);
				sequenceUntilFailModuleC1.addModule(setVariableCubeDroppedModule1);
				sequenceUntilFailModuleC1.addModule(followLineForAShortWhile1);	
				sequenceUntilFailModuleC1.addModule(setVariableRoaming1);	
		
		return succeederModuleSA1;
	}
	
	
	

}
