package context_circuit.gates;

public class GateNOT extends Gate {
	
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
	
}
