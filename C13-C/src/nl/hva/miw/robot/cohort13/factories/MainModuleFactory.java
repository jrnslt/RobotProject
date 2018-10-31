package nl.hva.miw.robot.cohort13.factories;

import behaviour.modules.BehaviourModule;
import behaviour.modules.CalibratieModule;
import behaviour.modules.ClearConsoleModule;
import behaviour.modules.DelayModule;
import behaviour.modules.WaitForEnterKeyModule;
import behaviour.modules.logic.InverterModule;
import behaviour.modules.logic.LeafModule;
import behaviour.modules.logic.LoopModule;
import behaviour.modules.logic.SequenceModule;
import behaviour.modules.logic.SequenceUntilFailModule;
import behaviour.modules.logic.StateConditionModule;
import behaviour.modules.logic.SucceederModule;
import behaviour.modules.procedures.console.ConsoleModule;
import behaviour.modules.procedures.exit.GoodbyeModule;
import behaviour.modules.procedures.keuze_opdracht.GrabCubeModule;
import behaviour.modules.procedures.keuze_opdracht.KeuzeOpdrachtModule;
import behaviour.modules.procedures.keuze_opdracht.RoamingModule;
import behaviour.modules.procedures.keuze_opdracht.VictoryDanceOpdracht2Module;
import behaviour.modules.procedures.parcour.ParcoursModule;
import behaviour.modules.procedures.parcour.ParcoursModuleRGB;
import behaviour.modules.procedures.parcour.ParcoursModuleRGB2;
import behaviour.modules.procedures.parcour.ParcoursModuleRgbCalibrate;
import behaviour.modules.procedures.testing.ColorSensorTesterModule;
import behaviour.modules.procedures.testing.CubeRecognizerTestModule;

import behaviour.modules.procedures.testing.ColorRecognizerTestModule;
import behaviour.modules.procedures.testing.DriveForwardTesterModule;
import behaviour.modules.procedures.testing.EndTestMessageModule;
import behaviour.modules.procedures.testing.LijnenTester;
import behaviour.modules.procedures.testing.ProximitySensorTesterModule;
import behaviour.modules.procedures.testing.RodeLijnTester;
import behaviour.modules.procedures.testing.StartTestMessageModule;
import behaviour.modules.procedures.uitbreiding.RegenBoogChecker;
import behaviour.modules.procedures.uitbreiding.UitbreidingOpdrachtModule;
import behaviour.modules.procedures.welcome.WelcomeModule;
import behaviour.modules.sound.BeepModule;
import behaviour.modules.sound.BeepSequenceModule;
import behaviour.modules.sound.PlaySampleModule;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.MarvinState;
import nl.hva.miw.robot.cohort13.resources.Sounds;
import nl.hva.miw.robot.cohort13.resources.TestingProcedureNames;

public class MainModuleFactory extends ModuleFactory {

	@Override
	public BehaviourModule createModule(Marvin marvin) {		
		LeafModule leafModule = new LeafModule(marvin);
		SequenceModule sequenceModuleA = new SequenceModule(marvin);
		LoopModule loopModuleB = new LoopModule(marvin);
		SequenceModule sequenceModuleB = new SequenceModule(marvin);
		SucceederModule succeederModuleA = new SucceederModule(marvin);
		SucceederModule succeederModuleB = new SucceederModule(marvin);
		SucceederModule succeederModuleC = new SucceederModule(marvin);
		SucceederModule succeederModuleD = new SucceederModule(marvin);
		InverterModule inverterModule = new InverterModule(marvin);
		
		BehaviourModule opdracht2Module = new Opdracht2ModuleFactory().createModule(marvin);
		
		SequenceUntilFailModule sequenceUntilFailModule_A = new SequenceUntilFailModule(marvin);
		SequenceUntilFailModule sequenceUntilFailModule_B = new SequenceUntilFailModule(marvin);	
		SequenceUntilFailModule sequenceUntilFailModule_C = new SequenceUntilFailModule(marvin);
		SequenceUntilFailModule sequenceUntilFailModule_E = new SequenceUntilFailModule(marvin);
		
		GoodbyeModule goodbyeModule = new GoodbyeModule(marvin);
		SequenceModule sequenceModule_Testing = new SequenceModule(marvin);
		
		leafModule.addModule(sequenceModuleA);
			//Welcome
			sequenceModuleA.addModule(new WelcomeModule(marvin));
			sequenceModuleA.addModule(new DelayModule(marvin, 2000));
			//Main Loop
			sequenceModuleA.addModule(loopModuleB);
				//Sequence
				loopModuleB.addModule(sequenceModuleB);
					//Console
					sequenceModuleB.addModule(new BeepSequenceModule(marvin));
					sequenceModuleB.addModule(new ConsoleModule(marvin));
					
					//Testing
					sequenceModuleB.addModule(succeederModuleA);		
						succeederModuleA.addModule(sequenceUntilFailModule_A);
							sequenceUntilFailModule_A.addModule(new StateConditionModule(marvin, MarvinState.TESTING));
							sequenceUntilFailModule_A.addModule(new CalibratieModule(marvin));
							sequenceUntilFailModule_A.addModule(sequenceModule_Testing);



								sequenceModule_Testing.addModule(new RegenBoogChecker(marvin));
							/*
							sequenceModule_Testing.addModule(new GrabCubeModule(marvin));
							sequenceModule_Testing.addModule(new DelayModule(marvin, 1000));
							sequenceModule_Testing.addModule(new BeepModule(marvin));
							sequenceModule_Testing.addModule(new VictoryDanceOpdracht2Module(marvin));;
							*/

//							marvin.getColorSensorControlDown().getColorSensor(), TestingProcedureNames.colorSensorName + "_A"));
	


							sequenceUntilFailModule_A.addModule(new DelayModule(marvin, 500));	
							sequenceUntilFailModule_A.addModule(new ClearConsoleModule(marvin));
							
					
					//Parcours
							


					sequenceModuleB.addModule(succeederModuleB);	
						succeederModuleB.addModule(sequenceUntilFailModule_B);
							sequenceUntilFailModule_B.addModule(new StateConditionModule(marvin, MarvinState.PARCOUR));
							sequenceUntilFailModule_B.addModule(new WaitForEnterKeyModule(marvin));
							sequenceUntilFailModule_B.addModule(new LijnenTester(marvin));

							sequenceUntilFailModule_B.addModule(new BeepModule(marvin));
							sequenceUntilFailModule_B.addModule(new DelayModule(marvin, 500));
							sequenceUntilFailModule_B.addModule(new ClearConsoleModule(marvin));
					//Keuze Opdracht
					sequenceModuleB.addModule(succeederModuleC);	
						succeederModuleC.addModule(sequenceUntilFailModule_C);
							sequenceUntilFailModule_C.addModule(new StateConditionModule(marvin, MarvinState.KEUZE_OPDRACHT));
							sequenceUntilFailModule_C.addModule(new CalibratieModule(marvin));
							sequenceUntilFailModule_C.addModule(opdracht2Module);
							sequenceUntilFailModule_C.addModule(new DelayModule(marvin, 500));
							sequenceUntilFailModule_C.addModule(new ClearConsoleModule(marvin));
					//Uitbreiding Keuze opdracht
					sequenceModuleB.addModule(succeederModuleD);
						succeederModuleD.addModule(sequenceUntilFailModule_E);
							sequenceUntilFailModule_E.addModule(new StateConditionModule(marvin, MarvinState.SHOW));
							sequenceUntilFailModule_E.addModule(new CalibratieModule(marvin));	
							sequenceUntilFailModule_E.addModule(new UitbreidingOpdrachtModule(marvin));				
							sequenceUntilFailModule_E.addModule(new DelayModule(marvin, 500));
							sequenceUntilFailModule_E.addModule(new ClearConsoleModule(marvin));
					//Exit
					sequenceModuleB.addModule(inverterModule);
						inverterModule.addModule(new StateConditionModule(marvin, MarvinState.EXIT));	
			//GoodBye
			sequenceModuleA.addModule(goodbyeModule);
			
		return leafModule;
	}
	
}
