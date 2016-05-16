package context_circuit;

import java.util.HashMap;

import context_circuit.gates.Gate;

public class Circuit {
	
	private HashMap<String, Gate> gates;
	
	public Circuit(){
		gates = new HashMap<String, Gate>();
	}

	public void addGate(Gate g, String name) {
		if (!gates.containsKey(name)) {
			gates.put(name, g);
		}
	}
	
	public void connect(String inputName, String outputName) {
		Gate input = gates.get(inputName);
		Gate output = gates.get(outputName);
		
		input.addOutput(output);
		output.incrementInputCount();
	}
	
}
