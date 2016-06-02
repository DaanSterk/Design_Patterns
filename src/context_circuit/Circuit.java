package context_circuit;

import java.util.HashMap;

import circuit_gates.GateNeutral;
import context_circuit.gates.Gate;

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
			System.out.println("ERROR: Trying to add a gate that already exists. (name: " + name + ")");
			System.exit(0);
		}
	}
	
	public void connect(String inputName, String outputName) {
		if (!inputName.equals(outputName)) {
			Gate input = gates.get(inputName);
			Gate output = gates.get(outputName);
			
			input.addOutput(output);
			output.incrementInputCount();
		}
		else {
			System.out.println("ERROR: Trying to connect gate to itself. (name: " + inputName + ")");
			System.exit(0);
		}
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
	
	public int getGateAmount(){
		return gates.size();
	}
	
}
