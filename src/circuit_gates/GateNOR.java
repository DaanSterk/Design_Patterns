package circuit_gates;

import context_circuit.gates.Gate;

public class GateNOR extends Gate{

	@Override
	protected void applyLogic() {
		boolean outputValue = true;
		for (boolean value : inputValues) {
			if (value) {
				outputValue = false;
				break;
			}
		}
		emit(outputValue);
	}

	@Override
	public Gate copy() {
		return new GateNOR();
	}
	
}
