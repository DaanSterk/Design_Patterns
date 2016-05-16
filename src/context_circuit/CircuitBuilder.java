package context_circuit;

import java.util.HashMap;
import java.util.List;

import context_circuit.gates.Gate;
import context_circuit.gates.GateAND;
import context_circuit.gates.GateNAND;
import context_circuit.gates.GateNOR;
import context_circuit.gates.GateNOT;
import context_circuit.gates.GateOR;
import context_circuit.gates.GateXOR;

public class CircuitBuilder {
	
	private Circuit circuit;
	private HashMap<String, String> nodeDescriptionMap;
	private HashMap<String, List<String>> edgeDescriptionMap;
	
	public CircuitBuilder() {
		circuit = new Circuit();
		buildCircuit();
	}
	
	private void buildCircuit(){
		CircuitReader cr = CircuitReader.getInstance();
		cr.getCircuitFromFile("C://Users//Jeffrey Vervoort//Documents//workspace//design_patterns//src//data//circuit1.txt");
		nodeDescriptionMap = cr.getNodeDescriptionMap();
		edgeDescriptionMap = cr.getEdgeDescriptionMap();
		
		for(String key : nodeDescriptionMap.keySet()){
			addToCircuit(key, nodeDescriptionMap.get(key));
		}
	}
	
	private void addToCircuit(String name, String type) {
		Gate gate;
		
		switch (type) {
		case "NOT":
			gate = new GateNOT();
		case "AND":
			gate = new GateAND();
		case "OR":
			gate = new GateOR();
		case "NAND":
			gate = new GateNAND();
		case "NOR":
			gate = new GateNOR();
		case "XOR":
			gate = new GateXOR();
		default:
			gate = null;
		}
		
		circuit.addGate(gate, name);
	}
	
	public Circuit getCircuit() {
		return circuit;
	}
	
}
