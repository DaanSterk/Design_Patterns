package context_circuit;

import java.util.ArrayList;
import java.util.HashMap;

import context_circuit.gates.Gate;
import context_circuit.gates.GateNeutral;

public class Circuit {
	private CircuitController controller;
	
	private HashMap<String, Gate> gates;
	
	// TESTING
	public void track(String name) {
		gates.get(name).track();
	}
	
	public Circuit(){
		gates = new HashMap<String, Gate>();
	}

	public void addGate(Gate g, String name) {
		if (!gates.containsKey(name)) {
			g.setName(name);
			g.setView(controller.getView());
			controller.getView().drawGate(g);
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
	
	public void simulate() {
		for (String gateName : gates.keySet()) {
			if (gates.get(gateName) instanceof GateNeutral) {

				GateNeutral gateN = (GateNeutral) gates.get(gateName);
				gateN.start();
			}
		}
	}
	
	public void setController(CircuitController controller) {
		this.controller = controller;
	}
	
	public void setDelay(int delay) { // in milliseconds.
		for (String name : gates.keySet()) {
			gates.get(name).setDelay(delay);
		}
	}
	
	public void setRemember(String gateName, boolean value) {
		GateNeutral g = (GateNeutral)gates.get(gateName);
		g.setRemember(value);
	}
	
	public ArrayList<Boolean> getGateMemory(String gateName) {
		GateNeutral g = (GateNeutral)gates.get(gateName);
		return g.getMemory();
	}
	
}
