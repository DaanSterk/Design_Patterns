package context_circuit.gates;

import java.util.ArrayList;
import view.CircuitPanel;

public abstract class Gate implements AbstractGate{

	protected String name;

	protected CircuitPanel view;

	protected ArrayList<Boolean> inputValues;
	protected ArrayList<Gate> outputGates;

	protected int inputCount;
	protected int outputValue;
	private int delay;

	// TESTING
	private boolean isTracked;

	public void track() {
		isTracked = true;
	}

	public Gate() {
		inputValues = new ArrayList<Boolean>();
		outputGates = new ArrayList<Gate>();

		delay = 1000; // Default
	}

	protected abstract void applyLogic();

	protected void emit(boolean value) {
		inputValues.clear();
		setOutputValue(value);
		if (isTracked) {
			String binary;
			if (value) binary = "1"; else binary = "0";
			System.out.println("[Tracker] (" + getType() + ") " + name + ": " + binary);
		}
		view.showOutput(name, value);
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (Gate g : outputGates) {
			g.receive(value);
		}
	}

	public void receive(boolean value) {
		inputValues.add(value);
		if (inputValues.size() >= inputCount) { // >= instead of == because of
												// starting gates having
												// inputCount = 0
			applyLogic();
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

	public void setView(CircuitPanel view) {
		this.view = view;
	}

	public ArrayList<Gate> getOutputGates() {
		return outputGates;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public int getOutputValue() {
		return outputValue;
	}
	
	private void setOutputValue(boolean output) {
		if(output)
			outputValue = 1;
		else
			outputValue = 0;
	}
}
