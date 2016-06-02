package circuit_gates;

import context_circuit.gates.Gate;

public class GateNOT extends Gate{
	
	@Override
	protected void applyLogic() {
		emit(!inputValues.get(0));
	}
	
	@Override // NOT gate may only receive one input.
	public void incrementInputCount() {
		if (inputCount < 1) {
			super.incrementInputCount();
		}
	}

	@Override
	public Gate copy() {
		return new GateNOT();
	}

}
