package context_circuit.gates;

import java.util.ArrayList;

public abstract class Gate {
	
	protected ArrayList<Boolean> inputValues;
	protected ArrayList<Gate> outputGates;
	
	protected int inputCount;
	
	
	public Gate() {	
		outputGates = new ArrayList<Gate>();
	}
	
	abstract void applyLogic();
	
	protected void emit(boolean value) {
		for (Gate g : outputGates) {
			g.receive(value);
		}
	}
	
	public void receive(boolean value) {
		inputValues.add(value);
		if (inputValues.size() == inputCount) {
			applyLogic();
		}
	}
	
	public void addOutput(Gate g) {
		outputGates.add(g);
	}
	
	public void incrementInputCount() {
		inputCount++;
	}
	
}
