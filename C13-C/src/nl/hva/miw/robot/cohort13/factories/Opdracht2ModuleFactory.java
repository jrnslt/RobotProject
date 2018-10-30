package nl.hva.miw.robot.cohort13.factories;

import behaviour.modules.BehaviourModule;
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
import behaviour.modules.procedures.keuze_opdracht.DropCubeModule;
import behaviour.modules.procedures.keuze_opdracht.FollowLineUntilCubeIsFoundModule;
import behaviour.modules.procedures.keuze_opdracht.FollowLineUntilDropSpotModule;
import behaviour.modules.procedures.keuze_opdracht.GrabCubeModule;
import behaviour.modules.procedures.keuze_opdracht.ResetVariablesOpdracht2Module;
import behaviour.modules.procedures.keuze_opdracht.RijdenNaarMiddenOpdracht2Module;
import behaviour.modules.procedures.keuze_opdracht.SetVariableBlueCubeDroppedModule;
import behaviour.modules.procedures.keuze_opdracht.SetVariableBlueCubeFoundModule;
import behaviour.modules.procedures.keuze_opdracht.SetVariableGreenCubeDroppedModule;
import behaviour.modules.procedures.keuze_opdracht.SetVariableGreenCubeFoundModule;
import behaviour.modules.procedures.keuze_opdracht.SetVariableRedCubeDroppedModule;
import behaviour.modules.procedures.keuze_opdracht.SetVariableRedCubeFoundModule;
import behaviour.modules.procedures.keuze_opdracht.VictoryDanceOpdracht2Module;
import nl.hva.miw.robot.cohort13.Marvin;

public class Opdracht2ModuleFactory extends ModuleFactory {

	@Override
	public BehaviourModule createModule(Marvin marvin) {
		LeafModule leafModule = new LeafModule(marvin);
			SequenceModule sequenceModule = new SequenceModule(marvin);
				ResetVariablesOpdracht2Module resetVariablesOpdracht2Module = new ResetVariablesOpdracht2Module(marvin);
				LoopModule loopModule = new LoopModule(marvin);
					SequenceUntilFailModule sequenceUntilFailModuleA = new SequenceUntilFailModule(marvin);
						//Red
						SequenceModule sequenceModuleA1 = new SequenceModule(marvin);
							SucceederModule succeederModuleA1 = new SucceederModule(marvin);
								SequenceUntilFailModule sequenceUntilFailModuleB1 = new SequenceUntilFailModule(marvin);
									ConditionRedCubeFoundModule conditionRedCubeFoundModuleA1 = new ConditionRedCubeFoundModule(marvin, false);
									FollowLineUntilCubeIsFoundModule followLineUntilDropSpotModuleA1 = new FollowLineUntilCubeIsFoundModule(marvin);
									GrabCubeModule grabCubeModule1 = new GrabCubeModule(marvin);
									SetVariableRedCubeFoundModule setVariableRedCubeFound1 = new SetVariableRedCubeFoundModule(marvin, true);		
							SucceederModule succeederModuleB1 = new SucceederModule(marvin);
								SequenceUntilFailModule sequenceUntilFailModuleC1 = new SequenceUntilFailModule(marvin);
									ConditionRedCubeFoundModule conditionRedCubeFoundModuleB1 = new ConditionRedCubeFoundModule(marvin, true);
									ConditionRedCubeDroppedModule conditionRedCubeDroppedModuleB1 = new ConditionRedCubeDroppedModule(marvin, false);
									FollowLineUntilDropSpotModule followLineUntilDropSpot1 = new FollowLineUntilDropSpotModule(marvin);
									DropCubeModule dropCubeModuleA1 = new DropCubeModule(marvin);
									SetVariableRedCubeDroppedModule setVariableRedCubeDroppedModule1 = new SetVariableRedCubeDroppedModule(marvin, true);
						
						//Green
						SequenceModule sequenceModuleA2 = new SequenceModule(marvin);
							SucceederModule succeederModuleA2 = new SucceederModule(marvin);
								SequenceUntilFailModule sequenceUntilFailModuleB2 = new SequenceUntilFailModule(marvin);
									ConditionGreenCubeFoundModule conditionGreenCubeFoundModuleA2 = new ConditionGreenCubeFoundModule(marvin, false);
									FollowLineUntilCubeIsFoundModule followLineUntilDropSpotModuleA2 = new FollowLineUntilCubeIsFoundModule(marvin);
									GrabCubeModule grabCubeModule2 = new GrabCubeModule(marvin);
									SetVariableGreenCubeFoundModule setVariableGreenCubeFound2 = new SetVariableGreenCubeFoundModule(marvin, true);			
							SucceederModule succeederModuleB2 = new SucceederModule(marvin);
								SequenceUntilFailModule sequenceUntilFailModuleC2 = new SequenceUntilFailModule(marvin);
									ConditionGreenCubeFoundModule conditionGreenCubeFoundModuleB2 = new ConditionGreenCubeFoundModule(marvin, true);
									ConditionGreenCubeDroppedModule conditionGreenCubeDroppedModuleB2 = new ConditionGreenCubeDroppedModule(marvin, false);
									FollowLineUntilDropSpotModule followLineUntilDropSpot2 = new FollowLineUntilDropSpotModule(marvin);
									DropCubeModule dropCubeModuleA2 = new DropCubeModule(marvin);
									SetVariableGreenCubeDroppedModule setVariableGreenCubeDroppedModule2 = new SetVariableGreenCubeDroppedModule(marvin, true);
					
						//Blue
						SequenceModule sequenceModuleA3 = new SequenceModule(marvin);
							SucceederModule succeederModuleA3 = new SucceederModule(marvin);
								SequenceUntilFailModule sequenceUntilFailModuleB3 = new SequenceUntilFailModule(marvin);
									ConditionBlueCubeFoundModule conditionBlueCubeFoundModuleA3 = new ConditionBlueCubeFoundModule(marvin, false);
									FollowLineUntilCubeIsFoundModule followLineUntilDropSpotModuleA3 = new FollowLineUntilCubeIsFoundModule(marvin);
									GrabCubeModule grabCubeModule3 = new GrabCubeModule(marvin);
									SetVariableBlueCubeFoundModule setVariableBlueCubeFound3 = new SetVariableBlueCubeFoundModule(marvin, true);			
							SucceederModule succeederModuleB3 = new SucceederModule(marvin);
								SequenceUntilFailModule sequenceUntilFailModuleC3 = new SequenceUntilFailModule(marvin);
									ConditionBlueCubeFoundModule conditionBlueCubeFoundModuleB3 = new ConditionBlueCubeFoundModule(marvin, true);
									ConditionBlueCubeDroppedModule conditionBlueCubeDroppedModuleB3 = new ConditionBlueCubeDroppedModule(marvin, false);
									FollowLineUntilDropSpotModule followLineUntilDropSpot3 = new FollowLineUntilDropSpotModule(marvin);
									DropCubeModule dropCubeModuleA3 = new DropCubeModule(marvin);
									SetVariableBlueCubeDroppedModule setVariableBlueCubeDroppedModule3 = new SetVariableBlueCubeDroppedModule(marvin, true);
						
						//Check
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
					//Red
					sequenceUntilFailModuleA.addModule(sequenceModuleA1);
						sequenceModuleA1.addModule(succeederModuleA1);
							succeederModuleA1.addModule(sequenceUntilFailModuleB1);
								sequenceUntilFailModuleB1.addModule(conditionRedCubeFoundModuleA1);
								sequenceUntilFailModuleB1.addModule(followLineUntilDropSpotModuleA1);	
								sequenceUntilFailModuleB1.addModule(grabCubeModule1);	
								sequenceUntilFailModuleB1.addModule(setVariableRedCubeFound1);	
						sequenceModuleA1.addModule(succeederModuleB1);			
							succeederModuleB1.addModule(sequenceUntilFailModuleC1);
								sequenceUntilFailModuleC1.addModule(conditionRedCubeFoundModuleB1);
								sequenceUntilFailModuleC1.addModule(conditionRedCubeDroppedModuleB1);
								sequenceUntilFailModuleC1.addModule(followLineUntilDropSpot1);
								sequenceUntilFailModuleC1.addModule(dropCubeModuleA1);
								sequenceUntilFailModuleC1.addModule(setVariableRedCubeDroppedModule1);
					//Green
					sequenceUntilFailModuleA.addModule(sequenceModuleA2);
						sequenceModuleA2.addModule(succeederModuleA2);
							succeederModuleA2.addModule(sequenceUntilFailModuleB2);
								sequenceUntilFailModuleB2.addModule(conditionGreenCubeFoundModuleA2);
								sequenceUntilFailModuleB2.addModule(followLineUntilDropSpotModuleA2);	
								sequenceUntilFailModuleB2.addModule(grabCubeModule2);	
								sequenceUntilFailModuleB2.addModule(setVariableGreenCubeFound2);	
						sequenceModuleA2.addModule(succeederModuleB2);			
							succeederModuleB2.addModule(sequenceUntilFailModuleC2);
								sequenceUntilFailModuleC2.addModule(conditionGreenCubeFoundModuleB2);
								sequenceUntilFailModuleC2.addModule(conditionGreenCubeDroppedModuleB2);
								sequenceUntilFailModuleC2.addModule(followLineUntilDropSpot2);
								sequenceUntilFailModuleC2.addModule(dropCubeModuleA2);
								sequenceUntilFailModuleC2.addModule(setVariableGreenCubeDroppedModule2);					
					//Blue
					sequenceUntilFailModuleA.addModule(sequenceModuleA3);
						sequenceModuleA3.addModule(succeederModuleA3);
							succeederModuleA3.addModule(sequenceUntilFailModuleB3);
								sequenceUntilFailModuleB3.addModule(conditionBlueCubeFoundModuleA3);
								sequenceUntilFailModuleB3.addModule(followLineUntilDropSpotModuleA3);	
								sequenceUntilFailModuleB3.addModule(grabCubeModule3);	
								sequenceUntilFailModuleB3.addModule(setVariableBlueCubeFound3);	
						sequenceModuleA3.addModule(succeederModuleB3);			
							succeederModuleB3.addModule(sequenceUntilFailModuleC3);
								sequenceUntilFailModuleC3.addModule(conditionBlueCubeFoundModuleB3);
								sequenceUntilFailModuleC3.addModule(conditionBlueCubeDroppedModuleB3);
								sequenceUntilFailModuleC3.addModule(followLineUntilDropSpot3);
								sequenceUntilFailModuleC3.addModule(dropCubeModuleA3);
								sequenceUntilFailModuleC3.addModule(setVariableBlueCubeDroppedModule3);				
					//Check
					sequenceUntilFailModuleA.addModule(sequenceUntilFailModule4);
						sequenceUntilFailModule4.addModule(conditionRedCubeFoundModule4);
						sequenceUntilFailModule4.addModule(conditionRedCubeDroppedModule4);
						sequenceUntilFailModule4.addModule(conditionGreenCubeFoundModule4);
						sequenceUntilFailModule4.addModule(conditionGreenCubeDroppedModule4);
						sequenceUntilFailModule4.addModule(conditionBlueCubeFoundModule4);
						sequenceUntilFailModule4.addModule(conditionBlueCubeDroppedModule4);
			sequenceModule.addModule(rijdenNaarMiddenOpdracht2Module);		
			sequenceModule.addModule(victoryDanceOpdracht2Module);			
								
		return leafModule;
	}

}
