package circuit_gates;

import context_circuit.gates.Gate;

public class GateOR extends Gate{

	@Override
	protected void applyLogic() {
		boolean outputValue = false;
		for (boolean value : inputValues) {
			if (value) {
				outputValue = true;
				break;
			}
		}
		emit(outputValue);
	}

	@Override
	public Gate copy() {
		return new GateOR();
	}

}
