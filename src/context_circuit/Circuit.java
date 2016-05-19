package context_circuit;

import java.util.ArrayList;
import java.util.HashMap;

import context_circuit.gates.Gate;

public class Circuit {
	
	private HashMap<String, Gate> gates;
	private HashMap<String, Boolean> startingValues;
	
	// TESTING
	public void track(String name) {
		gates.get(name).track();
	}
	
	public Circuit(){
		gates = new HashMap<String, Gate>();
		startingValues = new HashMap<String, Boolean>();
	}

	public void addGate(Gate g, String name) {
		if (!gates.containsKey(name)) {
			g.setName(name);
			gates.put(name, g);
		}
		else {
			// TODO exception 'gate already exists'.
		}
	}
	
	public void connect(String inputName, String outputName) {
		Gate input = gates.get(inputName);
		Gate output = gates.get(outputName);
		
		input.addOutput(output);
		output.incrementInputCount();
	}
	
	public void setStartingValue(String gateName, boolean value) {
		startingValues.put(gateName, value);
	}
	
	public void simulate() {
		for (String gateName : startingValues.keySet()) {
			gates.get(gateName).receive(startingValues.get(gateName));
		}
	}
	
}
