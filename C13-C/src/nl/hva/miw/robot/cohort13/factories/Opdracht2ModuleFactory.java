package nl.hva.miw.robot.cohort13.factories;

import behaviour.modules.BehaviourModule;
import behaviour.modules.logic.InverterModule;
import behaviour.modules.logic.LeafModule;
import behaviour.modules.logic.LoopModule;
import behaviour.modules.logic.SequenceModule;
import behaviour.modules.logic.SequenceUntilFailModule;
import behaviour.modules.logic.SucceederModule;
import behaviour.modules.procedures.keuze_opdracht.ConditionBlueCubeDroppedModule;
import behaviour.modules.procedures.keuze_opdracht.ConditionBlueCubeFoundModule;
import behaviour.modules.procedures.keuze_opdracht.ConditionGreenCubeDroppedModule;
import behaviour.modules.procedures.keuze_opdracht.ConditionGreenCubeFoundModule;
import behaviour.modules.procedures.keuze_opdracht.ConditionRedCubeDroppedModule;
import behaviour.modules.procedures.keuze_opdracht.ConditionRedCubeFoundModule;
import behaviour.modules.procedures.keuze_opdracht.ConditionRoaming;
import behaviour.modules.procedures.keuze_opdracht.DropCubeModule;
import behaviour.modules.procedures.keuze_opdracht.FollowLineForAShortWhile;
import behaviour.modules.procedures.keuze_opdracht.FollowLineUntilCubeIsFoundModule;
import behaviour.modules.procedures.keuze_opdracht.FollowLineUntilDropSpotModule;
import behaviour.modules.procedures.keuze_opdracht.GrabCubeModule;
import behaviour.modules.procedures.keuze_opdracht.ResetVariablesOpdracht2Module;
import behaviour.modules.procedures.keuze_opdracht.RijdenNaarMiddenOpdracht2Module;
import behaviour.modules.procedures.keuze_opdracht.RoamingModule;
import behaviour.modules.procedures.keuze_opdracht.SetVariableBlueCubeDroppedModule;
import behaviour.modules.procedures.keuze_opdracht.SetVariableBlueCubeFoundModule;
import behaviour.modules.procedures.keuze_opdracht.SetVariableGreenCubeDroppedModule;
import behaviour.modules.procedures.keuze_opdracht.SetVariableGreenCubeFoundModule;
import behaviour.modules.procedures.keuze_opdracht.SetVariableRedCubeDroppedModule;
import behaviour.modules.procedures.keuze_opdracht.SetVariableRedCubeFoundModule;
import behaviour.modules.procedures.keuze_opdracht.SetVariableRoaming;
import behaviour.modules.procedures.keuze_opdracht.VictoryDanceOpdracht2Module;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.resources.Colors;

public class Opdracht2ModuleFactory extends ModuleFactory {

	@Override
	public BehaviourModule createModule(Marvin marvin) {
		/*
		LeafModule leafModule = new LeafModule(marvin);
			SequenceModule sequenceModule = new SequenceModule(marvin);
				ResetVariablesOpdracht2Module resetVariablesOpdracht2Module = new ResetVariablesOpdracht2Module(marvin);
				LoopModule loopModule = new LoopModule(marvin);
					SequenceUntilFailModule sequenceUntilFailModuleA = new SequenceUntilFailModule(marvin);
						//Roaming
						SucceederModule succeederModule0 = new SucceederModule(marvin);
							SequenceModule sequenceModule0 = new SequenceModule(marvin);
								ConditionRoaming conditionRoaming0 = new ConditionRoaming(marvin, true);
								RoamingModule roamingModule0 = new RoamingModule(marvin);
								SetVariableRoaming setVariableRoaming0 = new SetVariableRoaming(marvin, false);
						//Red
						SucceederModule succeederModuleSA1 = new SucceederModule(marvin);
							SequenceUntilFailModule sequenceUntilFailModuleA1 = new SequenceUntilFailModule(marvin);
								ConditionRoaming conditionRoaming1 = new ConditionRoaming(marvin, false);
								SequenceUntilFailModule sequenceUntilFailModuleB1 = new SequenceUntilFailModule(marvin);
									ConditionRedCubeFoundModule conditionRedCubeFoundModuleA1 = new ConditionRedCubeFoundModule(marvin, false);
									FollowLineUntilCubeIsFoundModule followLineUntilDropSpotModuleA1 = new FollowLineUntilCubeIsFoundModule(marvin, Colors.RED);
									GrabCubeModule grabCubeModule1 = new GrabCubeModule(marvin);
									SetVariableRedCubeFoundModule setVariableRedCubeFound1 = new SetVariableRedCubeFoundModule(marvin, true);		
								SequenceUntilFailModule sequenceUntilFailModuleC1 = new SequenceUntilFailModule(marvin);
									ConditionRedCubeFoundModule conditionRedCubeFoundModuleB1 = new ConditionRedCubeFoundModule(marvin, true);
									ConditionRedCubeDroppedModule conditionRedCubeDroppedModuleB1 = new ConditionRedCubeDroppedModule(marvin, false);
									FollowLineUntilDropSpotModule followLineUntilDropSpot1 = new FollowLineUntilDropSpotModule(marvin, Colors.RED);
									DropCubeModule dropCubeModuleA1 = new DropCubeModule(marvin);
									SetVariableRedCubeDroppedModule setVariableRedCubeDroppedModule1 = new SetVariableRedCubeDroppedModule(marvin, true);
									FollowLineForAShortWhile followLineForAShortWhile1 = new FollowLineForAShortWhile(marvin);	
									SetVariableRoaming setVariableRoaming1 = new SetVariableRoaming(marvin, true);
						//Green
<<<<<<< HEAD
						SequenceModule sequenceModuleA2 = new SequenceModule(marvin);
							SucceederModule succeederModuleA2 = new SucceederModule(marvin);
								SequenceUntilFailModule sequenceUntilFailModuleB2 = new SequenceUntilFailModule(marvin);
									ConditionGreenCubeFoundModule conditionGreenCubeFoundModuleA2 = new ConditionGreenCubeFoundModule(marvin, false);
									FollowLineUntilCubeIsFoundModule followLineUntilDropSpotModuleA2 = new FollowLineUntilCubeIsFoundModule(marvin, Colors.GREEN);
									GrabCubeModule grabCubeModule2 = new GrabCubeModule(marvin);
									SetVariableGreenCubeFoundModule setVariableGreenCubeFound2 = new SetVariableGreenCubeFoundModule(marvin, true);			
							SucceederModule succeederModuleB2 = new SucceederModule(marvin);
								SequenceUntilFailModule sequenceUntilFailModuleC2 = new SequenceUntilFailModule(marvin);
									ConditionGreenCubeFoundModule conditionGreenCubeFoundModuleB2 = new ConditionGreenCubeFoundModule(marvin, true);
									ConditionGreenCubeDroppedModule conditionGreenCubeDroppedModuleB2 = new ConditionGreenCubeDroppedModule(marvin, false);
									FollowLineUntilDropSpotModule followLineUntilDropSpot2 = new FollowLineUntilDropSpotModule(marvin, Colors.GREEN);
									DropCubeModule dropCubeModuleA2 = new DropCubeModule(marvin);
									SetVariableGreenCubeDroppedModule setVariableGreenCubeDroppedModule2 = new SetVariableGreenCubeDroppedModule(marvin, true);
					
						//Blue
						SequenceModule sequenceModuleA3 = new SequenceModule(marvin);
							SucceederModule succeederModuleA3 = new SucceederModule(marvin);
								SequenceUntilFailModule sequenceUntilFailModuleB3 = new SequenceUntilFailModule(marvin);
									ConditionBlueCubeFoundModule conditionBlueCubeFoundModuleA3 = new ConditionBlueCubeFoundModule(marvin, false);
									FollowLineUntilCubeIsFoundModule followLineUntilDropSpotModuleA3 = new FollowLineUntilCubeIsFoundModule(marvin, Colors.BLUE);
									GrabCubeModule grabCubeModule3 = new GrabCubeModule(marvin);
									SetVariableBlueCubeFoundModule setVariableBlueCubeFound3 = new SetVariableBlueCubeFoundModule(marvin, true);			
							SucceederModule succeederModuleB3 = new SucceederModule(marvin);
								SequenceUntilFailModule sequenceUntilFailModuleC3 = new SequenceUntilFailModule(marvin);
									ConditionBlueCubeFoundModule conditionBlueCubeFoundModuleB3 = new ConditionBlueCubeFoundModule(marvin, true);
									ConditionBlueCubeDroppedModule conditionBlueCubeDroppedModuleB3 = new ConditionBlueCubeDroppedModule(marvin, false);
									FollowLineUntilDropSpotModule followLineUntilDropSpot3 = new FollowLineUntilDropSpotModule(marvin, Colors.BLUE);
									DropCubeModule dropCubeModuleA3 = new DropCubeModule(marvin);
									SetVariableBlueCubeDroppedModule setVariableBlueCubeDroppedModule3 = new SetVariableBlueCubeDroppedModule(marvin, true);
						
=======
						SucceederModule succeederModuleSA2 = new SucceederModule(marvin);
						SequenceUntilFailModule sequenceUntilFailModuleA2 = new SequenceUntilFailModule(marvin);
							ConditionRoaming conditionRoaming2 = new ConditionRoaming(marvin, false);
							SequenceUntilFailModule sequenceUntilFailModuleB2 = new SequenceUntilFailModule(marvin);
								ConditionGreenCubeFoundModule conditionGreenCubeFoundModuleA2 = new ConditionGreenCubeFoundModule(marvin, false);
								FollowLineUntilCubeIsFoundModule followLineUntilDropSpotModuleA2 = new FollowLineUntilCubeIsFoundModule(marvin);
								GrabCubeModule grabCubeModule2 = new GrabCubeModule(marvin);
								SetVariableGreenCubeFoundModule setVariableGreenCubeFound2 = new SetVariableGreenCubeFoundModule(marvin, true);		
							SequenceUntilFailModule sequenceUntilFailModuleC2 = new SequenceUntilFailModule(marvin);
								ConditionGreenCubeFoundModule conditionGreenCubeFoundModuleB2 = new ConditionGreenCubeFoundModule(marvin, true);
								ConditionGreenCubeDroppedModule conditionGreenCubeDroppedModuleB2 = new ConditionGreenCubeDroppedModule(marvin, false);
								FollowLineUntilDropSpotModule followLineUntilDropSpot2 = new FollowLineUntilDropSpotModule(marvin);
								DropCubeModule dropCubeModuleA2 = new DropCubeModule(marvin);
								SetVariableGreenCubeDroppedModule setVariableGreenCubeDroppedModule2 = new SetVariableGreenCubeDroppedModule(marvin, true);
								FollowLineForAShortWhile followLineForAShortWhile2 = new FollowLineForAShortWhile(marvin);	
								SetVariableRoaming setVariableRoaming2 = new SetVariableRoaming(marvin, true);
								
						//Blue
						SucceederModule succeederModuleSA3 = new SucceederModule(marvin);
						SequenceUntilFailModule sequenceUntilFailModuleA3 = new SequenceUntilFailModule(marvin);
							ConditionRoaming conditionRoaming3 = new ConditionRoaming(marvin, false);
							SequenceUntilFailModule sequenceUntilFailModuleB3 = new SequenceUntilFailModule(marvin);
								ConditionBlueCubeFoundModule conditionGreenCubeFoundModuleA3 = new ConditionBlueCubeFoundModule(marvin, false);
								FollowLineUntilCubeIsFoundModule followLineUntilDropSpotModuleA3 = new FollowLineUntilCubeIsFoundModule(marvin);
								GrabCubeModule grabCubeModule3 = new GrabCubeModule(marvin);
								SetVariableBlueCubeFoundModule setVariableGreenCubeFound3 = new SetVariableBlueCubeFoundModule(marvin, true);		
							SequenceUntilFailModule sequenceUntilFailModuleC3 = new SequenceUntilFailModule(marvin);
								ConditionBlueCubeFoundModule conditionGreenCubeFoundModuleB3 = new ConditionBlueCubeFoundModule(marvin, true);
								ConditionBlueCubeDroppedModule conditionGreenCubeDroppedModuleB3 = new ConditionBlueCubeDroppedModule(marvin, false);
								FollowLineUntilDropSpotModule followLineUntilDropSpot3 = new FollowLineUntilDropSpotModule(marvin);
								DropCubeModule dropCubeModuleA3 = new DropCubeModule(marvin);
								SetVariableBlueCubeDroppedModule setVariableGreenCubeDroppedModule3 = new SetVariableBlueCubeDroppedModule(marvin, true);
								FollowLineForAShortWhile followLineForAShortWhile3 = new FollowLineForAShortWhile(marvin);	
								SetVariableRoaming setVariableRoaming3 = new SetVariableRoaming(marvin, true);
								
>>>>>>> b8dc5e9c5e667daa3262907f61fa6baf22681efd
						//Check
						InverterModule inverterModule4 = new InverterModule(marvin);	
							SequenceUntilFailModule sequenceUntilFailModule4 = new SequenceUntilFailModule(marvin);
								ConditionRedCubeFoundModule conditionRedCubeFoundModule4 = new ConditionRedCubeFoundModule(marvin, true);
								ConditionRedCubeDroppedModule conditionRedCubeDroppedModule4 = new ConditionRedCubeDroppedModule(marvin, true);
								ConditionGreenCubeFoundModule conditionGreenCubeFoundModule4 = new ConditionGreenCubeFoundModule(marvin, true);
								ConditionGreenCubeDroppedModule conditionGreenCubeDroppedModule4 = new ConditionGreenCubeDroppedModule(marvin, true);				
								ConditionBlueCubeFoundModule conditionBlueCubeFoundModule4 = new ConditionBlueCubeFoundModule(marvin, true);
								ConditionBlueCubeDroppedModule conditionBlueCubeDroppedModule4 = new ConditionBlueCubeDroppedModule(marvin, true);							
				//Einde		
				RijdenNaarMiddenOpdracht2Module rijdenNaarMiddenOpdracht2Module = new RijdenNaarMiddenOpdracht2Module(marvin);	
				VictoryDanceOpdracht2Module victoryDanceOpdracht2Module = new VictoryDanceOpdracht2Module(marvin);
			
		
		leafModule.addModule(sequenceModule);
			sequenceModule.addModule(resetVariablesOpdracht2Module);
			sequenceModule.addModule(loopModule);	
				loopModule.addModule(sequenceUntilFailModuleA);
					//Roaming
					sequenceUntilFailModuleA.addModule(succeederModule0);
						succeederModule0.addModule(sequenceModule0);
							sequenceModule0.addModule(conditionRoaming0);
							sequenceModule0.addModule(roamingModule0);
							sequenceModule0.addModule(setVariableRoaming0);	
					//Red
					sequenceUntilFailModuleA.addModule(succeederModuleSA1);
						succeederModuleSA1.addModule(sequenceUntilFailModuleA1);
							sequenceUntilFailModuleA1.addModule(conditionRoaming1);
							sequenceUntilFailModuleA1.addModule(sequenceUntilFailModuleB1);
								sequenceUntilFailModuleB1.addModule(conditionRedCubeFoundModuleA1);
								sequenceUntilFailModuleB1.addModule(followLineUntilDropSpotModuleA1);	
								sequenceUntilFailModuleB1.addModule(grabCubeModule1);	
								sequenceUntilFailModuleB1.addModule(setVariableRedCubeFound1);	
							sequenceUntilFailModuleA1.addModule(sequenceUntilFailModuleC1);			
								sequenceUntilFailModuleC1.addModule(conditionRedCubeFoundModuleB1);
								sequenceUntilFailModuleC1.addModule(conditionRedCubeDroppedModuleB1);
								sequenceUntilFailModuleC1.addModule(followLineUntilDropSpot1);
								sequenceUntilFailModuleC1.addModule(dropCubeModuleA1);
								sequenceUntilFailModuleC1.addModule(setVariableRedCubeDroppedModule1);
								sequenceUntilFailModuleC1.addModule(followLineForAShortWhile1);	
								sequenceUntilFailModuleC1.addModule(setVariableRoaming1);	
					//Green
					sequenceUntilFailModuleA.addModule(succeederModuleSA2);
					succeederModuleSA2.addModule(sequenceUntilFailModuleA2);
						sequenceUntilFailModuleA2.addModule(conditionRoaming2);
						sequenceUntilFailModuleA2.addModule(sequenceUntilFailModuleB2);
							sequenceUntilFailModuleB2.addModule(conditionGreenCubeFoundModuleA2);
							sequenceUntilFailModuleB2.addModule(followLineUntilDropSpotModuleA2);	
							sequenceUntilFailModuleB2.addModule(grabCubeModule2);	
							sequenceUntilFailModuleB2.addModule(setVariableGreenCubeFound2);	
						sequenceUntilFailModuleA2.addModule(sequenceUntilFailModuleC2);			
							sequenceUntilFailModuleC2.addModule(conditionGreenCubeFoundModuleB2);
							sequenceUntilFailModuleC2.addModule(conditionGreenCubeDroppedModuleB2);
							sequenceUntilFailModuleC2.addModule(followLineUntilDropSpot2);
							sequenceUntilFailModuleC2.addModule(dropCubeModuleA2);
							sequenceUntilFailModuleC2.addModule(setVariableGreenCubeDroppedModule2);	
							sequenceUntilFailModuleC2.addModule(followLineForAShortWhile2);	
							sequenceUntilFailModuleC2.addModule(setVariableRoaming2);	
					//Blue
					sequenceUntilFailModuleA.addModule(succeederModuleSA3);
					succeederModuleSA3.addModule(sequenceUntilFailModuleA3);
						sequenceUntilFailModuleA3.addModule(conditionRoaming3);
						sequenceUntilFailModuleA3.addModule(sequenceUntilFailModuleB3);
							sequenceUntilFailModuleB3.addModule(conditionGreenCubeFoundModuleA3);
							sequenceUntilFailModuleB3.addModule(followLineUntilDropSpotModuleA3);	
							sequenceUntilFailModuleB3.addModule(grabCubeModule3);	
							sequenceUntilFailModuleB3.addModule(setVariableGreenCubeFound3);	
						sequenceUntilFailModuleA3.addModule(sequenceUntilFailModuleC3);			
							sequenceUntilFailModuleC3.addModule(conditionGreenCubeFoundModuleB3);
							sequenceUntilFailModuleC3.addModule(conditionGreenCubeDroppedModuleB3);
							sequenceUntilFailModuleC3.addModule(followLineUntilDropSpot3);
							sequenceUntilFailModuleC3.addModule(dropCubeModuleA3);
							sequenceUntilFailModuleC3.addModule(setVariableGreenCubeDroppedModule3);	
							sequenceUntilFailModuleC3.addModule(followLineForAShortWhile3);	
							sequenceUntilFailModuleC3.addModule(setVariableRoaming3);	
					//Check
					sequenceUntilFailModuleA.addModule(inverterModule4);
						inverterModule4.addModule(sequenceUntilFailModule4);
							sequenceUntilFailModule4.addModule(conditionRedCubeFoundModule4);
							sequenceUntilFailModule4.addModule(conditionRedCubeDroppedModule4);
							sequenceUntilFailModule4.addModule(conditionGreenCubeFoundModule4);
							sequenceUntilFailModule4.addModule(conditionGreenCubeDroppedModule4);
							sequenceUntilFailModule4.addModule(conditionBlueCubeFoundModule4);
							sequenceUntilFailModule4.addModule(conditionBlueCubeDroppedModule4);
			sequenceModule.addModule(rijdenNaarMiddenOpdracht2Module);		
			sequenceModule.addModule(victoryDanceOpdracht2Module);			
								
								
		return leafModule;
		*/
		return new LeafModule(marvin);
	}

}
