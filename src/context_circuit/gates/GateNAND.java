package context_circuit.gates;

public class GateNAND extends Gate {

	@Override
	protected void applyLogic() {
		boolean outputValue = false;
		for (boolean value : inputValues) {
			if (!value) {
				outputValue = true;
				break;
			}
		}
		emit(outputValue);
	}
	
}
