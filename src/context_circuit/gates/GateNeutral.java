package context_circuit.gates;

public class GateNeutral extends Gate {
	
	private boolean isStarter;
	private boolean startingValue;
	
	@Override
	protected void applyLogic() {
		boolean outputValue = inputValues.get(0);
		
		emit(outputValue);
	}
	
	public void start() {
		// Show 'started' string in console...
		if (isStarter) {
			System.out.print("Starting " + name + " with value ");
			String binary;
			if (startingValue) {
				binary = "1";
			}
			else {
				binary = "0";
			}
			System.out.println(binary);
			
			// Start the chain
			emit(startingValue);
		}
	}
	
	public void setStartingValue(boolean value) {
		isStarter = true;
		startingValue = value;
	}
	
}
