package context_circuit.gates;

import java.util.ArrayList;

import context_circuit.Circuit;

public class GateNeutral extends Gate {

	private boolean remember;
	private ArrayList<Boolean> memory;
	
	private boolean isStarter;
	private boolean startingValue;
	
	public GateNeutral() {
		memory = new ArrayList<Boolean>();
	}
	
	@Override
	protected void applyLogic() {
		boolean outputValue = inputValues.get(0);
		
		if (remember) {
			memory.add(outputValue);
		}
		
		emit(outputValue);
	}
	
	public void start() {
		if (isStarter) {
			emit(startingValue);
		}
	}
	
	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	
	public ArrayList<Boolean> getMemory() {
		return memory;
	}
	
	public void setStartingValue(boolean value) {
		isStarter = true;
		startingValue = value;
	}
	
}
