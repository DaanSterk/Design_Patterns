import context_circuit.CircuitController;
import context_circuit.CircuitReader;

public class Main {

	public static void main(String[] args) {
		testReader();
		CircuitController circuitController = new CircuitController();
	}
	
	//TODO: Delete this method when done with the testing of the CircuitReader.
	private static void testReader(){
		CircuitReader cr = CircuitReader.getInstance();
		cr.getCircuitFromFile("C://Users//Jeffrey Vervoort//Documents//workspace//design_patterns//src//data//circuit1.txt");
		
		for(String key : cr.getNodeDescriptionMap().keySet()){
			System.out.println(key + " - " + cr.getNodeDescriptionMap().get(key));
		}
		System.out.println("#############################################");
		for(String key : cr.getEdgeDescriptionMap().keySet()){
			System.out.println(key + " - " + cr.getEdgeDescriptionMap().get(key));
		}
	}

}
