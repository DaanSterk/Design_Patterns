package circuit_gates;

import context_circuit.gates.Gate;

public class GateXOR extends Gate{

	@Override
	protected void applyLogic() {
		emit(inputValues.get(0) != inputValues.get(1));
	}
	
	@Override // XOR gate may only receive two inputs.
	public void incrementInputCount() {
		if (inputCount < 2) {
			super.incrementInputCount();
		}
	}

	@Override
	public Gate copy() {
		return new GateXOR();
	}
	
}
