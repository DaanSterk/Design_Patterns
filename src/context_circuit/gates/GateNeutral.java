package context_circuit.gates;

public class GateNeutral extends Gate {

	@Override
	protected void applyLogic() {
		boolean outputValue = inputValues.get(0);
		emit(outputValue);
	}
	
}
