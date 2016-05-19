package context_circuit.gates;

import java.util.ArrayList;

public abstract class Gate {
	
	private String name;
	
	protected ArrayList<Boolean> inputValues;
	protected ArrayList<Gate> outputGates;
	
	protected int inputCount;
	
	// TESTING
	private boolean isTracked;
	public void track() {
		isTracked = true;
	}
	
	public Gate() {	
		inputValues = new ArrayList<Boolean>();
		outputGates = new ArrayList<Gate>();
	}
	
	protected abstract void applyLogic();
	
	protected void emit(boolean value) {
		if (isTracked) {
	 		System.out.println("(" + getType() + ") " + name + ": " + value);
		}
		for (Gate g : outputGates) {
			g.receive(value);
		}
	}
	
	public void receive(boolean value) {
		inputValues.add(value);
		if (inputValues.size() >= inputCount) { // >= instead of == because of starting gates having inputCount = 0
			applyLogic();
			inputValues.clear();
		}
	}
	
	public void addOutput(Gate g) {
		outputGates.add(g);
	}
	
	public void incrementInputCount() {
		inputCount++;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return this.getClass().getSimpleName().substring(4);
	}
	
}
