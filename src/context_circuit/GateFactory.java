package context_circuit;

import context_circuit.gates.Gate;
import context_circuit.gates.GateAND;
import context_circuit.gates.GateNAND;
import context_circuit.gates.GateNOR;
import context_circuit.gates.GateNOT;
import context_circuit.gates.GateNeutral;
import context_circuit.gates.GateOR;
import context_circuit.gates.GateXOR;

public class GateFactory {
	
	public Gate getGate(String type) {
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
		case "INPUT_LOW":
			gate = new GateNeutral();
			GateNeutral gateN = (GateNeutral) gate;
			gateN.setStartingValue(false);
			break;
		case "INPUT_HIGH":
			gate = new GateNeutral();
			GateNeutral gateM = (GateNeutral) gate;
			gateM.setStartingValue(true);
			break;
		default:
			gate = new GateNeutral();
			break;
		}
		
		return gate;
	}
	
}
