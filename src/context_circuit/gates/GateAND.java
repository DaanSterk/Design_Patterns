package context_circuit.gates;

public class GateAND extends Gate {

	@Override
	protected void applyLogic() {
		boolean outputValue = true;
		for (boolean value : inputValues) {
			if (!value) {
				outputValue = false;
				break;
			}
		}
		emit(outputValue);
	}
	
}
