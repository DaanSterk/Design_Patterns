package context_circuit;

public class CircuitController {

	private Circuit circuit;
	private CircuitBuilder circuitBuilder;
	
	
	public CircuitController() {
		circuitBuilder = new CircuitBuilder();
		circuitBuilder.buildCircuitFromFile("E://Users//Daan//workspace//Design_Patterns//src//data//circuit1.txt");
	}
	
}
