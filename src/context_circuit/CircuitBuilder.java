package context_circuit;

import java.util.HashMap;
import java.util.List;

import circuit_gates.GateNeutral;
import context_circuit.gates.Gate;

public class CircuitBuilder {
	
	private Circuit circuit;
	//private GateFactory GateFactory;
	
	private HashMap<String, String> nodeDescriptionMap;
	private HashMap<String, List<String>> edgeDescriptionMap;
	
	public CircuitBuilder() {
		circuit = new Circuit();
		//GateFactory = new GateFactory();
	}
	
	public void buildCircuitFromFile(String path){
		CircuitReader circuitReader = CircuitReader.getInstance();
		circuitReader.getCircuitFromFile(path);
		nodeDescriptionMap = circuitReader.getNodeDescriptionMap();
		edgeDescriptionMap = circuitReader.getEdgeDescriptionMap();
		
		for(String key : nodeDescriptionMap.keySet()){
			addToCircuit(key, nodeDescriptionMap.get(key));
		}
		
		for(String inputGateName : edgeDescriptionMap.keySet()){
			for(String outputGateName : edgeDescriptionMap.get(inputGateName)){
				getCircuit().connect(inputGateName, outputGateName);
			}
		}
	}
	
	private void addToCircuit(String name, String type) {
		type = "Gate" + type;
		try{
			final Gate gate = GateFactory.create(name, type);
			if(type == "INPUT_LOW"){
				GateNeutral gateN = (GateNeutral) gate;
				gateN.setStartingValue(false);
			} else if(type == "INPUT_HIGH"){
				GateNeutral gateN = (GateNeutral) gate;
				gateN.setStartingValue(true);
			}
			
			getCircuit().addGate(gate, name);
		} 
		catch ( IllegalArgumentException exception ) {
			System.out.println( exception.getMessage() );
		}
	}
	
	public Circuit getCircuit() {
		return circuit;
	}
	
	public void passController(CircuitController c) {
		circuit.setController(c);
	}
	
}
