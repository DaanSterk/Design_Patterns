package context_circuit;

import java.util.HashMap;
import java.util.List;

import context_circuit.gates.Gate;

public class CircuitBuilder {
	
	private Circuit circuit;
	private GateFactory gateFactory;
	
	private HashMap<String, String> nodeDescriptionMap;
	private HashMap<String, List<String>> edgeDescriptionMap;
	
	
	public CircuitBuilder() {
		circuit = new Circuit();
		gateFactory = new GateFactory();
	}
	
	public void buildCircuitFromFile(String path){
		CircuitReader circuitReader = CircuitReader.getInstance();
		circuitReader.getCircuitFromFile(path); 	// Jeffrey: C://Users//Jeffrey Vervoort//Documents//workspace//design_patterns//src//data//circuit1.txt
													// Daan: 	E://Users//Daan//workspace//Design_Patterns//src//data//circuit1.txt
		nodeDescriptionMap = circuitReader.getNodeDescriptionMap();
		edgeDescriptionMap = circuitReader.getEdgeDescriptionMap();
		
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
	
	private void buildCircuitManually() {
		// Testcode
	}
	
	private void addToCircuit(String name, String type) {
		Gate gate = gateFactory.getGate(type);
		getCircuit().addGate(gate, name);
	}
	
	public Circuit getCircuit() {
		return circuit;
	}
	
}
