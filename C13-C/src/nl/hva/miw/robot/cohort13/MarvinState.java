package nl.hva.miw.robot.cohort13;

public enum MarvinState {

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
	
	public static MarvinState getStateByNumber(int number) {
		for (MarvinState state : MarvinState.values()) {
			 if (state.stateNumber == number) {
				 return state;
			 }
		}
		
		return MENU;
	}
}
