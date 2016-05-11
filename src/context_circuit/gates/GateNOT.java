package context_circuit.gates;

public class GateNOT extends Gate {
	
	@Override
	protected void applyLogic() {
		if (inputValues.get(0)) { 
			emit(false);
		}
	}
	
	@Override // NOT gate may only receive one input.
	public void incrementInputCount() {
		if (inputCount < 1) {
			super.incrementInputCount();
		}
	}
	
}
