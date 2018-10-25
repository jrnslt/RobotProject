package nl.hva.miw.robot.cohort13;

/*
 * MarvinState bestaat uit verschillende "staten". 
 * MarvinState kan enkel de onderstaande  6 staten bevatten 
 * Het heeft overeenkomsten met een Array
 */


public enum MarvinState {		//Deze moet gelijk zijn aan een van de onderstaande "staten" 

	MENU(0, "menu"),
	TESTING(1, "testing"),
	PARCOUR(2, "parcour"),
	KEUZE_OPDRACHT(3, "keuze opdracht"),
	SHOW(4, "show"),
	EXIT(5, "exit");
	
	public int stateNumber;
	public String stateName;
	
	MarvinState(int stateNumber, String stateName){ 
		this.stateNumber = stateNumber;
		this.stateName = stateName;
	}
	
	public static int getAmountOfStates() {
		return MarvinState.values().length;
	}
	
	public static MarvinState getStateByNumber(int number) { //Geeft een bepaalde staat terug, afhankelijk van de input
		for (MarvinState state : MarvinState.values()) {
			 if (state.stateNumber == number) {
				 return state;
			 }
		}
		
		return MENU;
	}
}
