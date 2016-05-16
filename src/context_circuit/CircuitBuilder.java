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
import context_circuit.gates.special.GateA;
import context_circuit.gates.special.GateB;
import context_circuit.gates.special.GateCin;
import context_circuit.gates.special.GateCout;
import context_circuit.gates.special.GateSum;

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
		
		for(String inputGateName : edgeDescriptionMap.keySet()){
			for(String outputGateName : edgeDescriptionMap.get(inputGateName)){
				System.out.println(inputGateName + "___/+\\___" + outputGateName);
				getCircuit().connect(inputGateName, outputGateName);
			}
		}
	}
	
	private void addToCircuit(String name, String type) {
		Gate gate;
		
		switch (type.toUpperCase()) {
		case "NOT":
			gate = new GateNOT();
			break;
		case "AND":
			gate = new GateAND();
			break;
		case "OR":
			gate = new GateOR();
			break;
		case "NAND":
			gate = new GateNAND();
			break;
		case "NOR":
			gate = new GateNOR();
			break;
		case "XOR":
			gate = new GateXOR();
			break;
//		case "A":
//			gate = new GateA();
//			break;
//		case "B":
//			gate = new GateB();
//			break;
//		case "CIN":
//			gate = new GateCin();
//			break;
//		case "COUT":
//			gate = new GateCout();
//			break;
//		case "SUM":
//			gate = new GateSum();
//			break;
		default:
			gate = null;
			break;
		}
		
		getCircuit().addGate(gate, name);
	}
	
	public Circuit getCircuit() {
		return circuit;
	}
	
}
