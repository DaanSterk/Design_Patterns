package context_circuit;

import javax.swing.JFrame;

import view.CircuitPanel;

public class CircuitController {

	private Circuit circuit;
	private CircuitBuilder circuitBuilder;
	
	// VIEW
	private JFrame frame;
	private CircuitPanel panel;
	
	
	public CircuitController() {
		circuitBuilder = new CircuitBuilder();
		circuitBuilder.buildCircuitFromFile("E://Users//Daan//workspace//Design_Patterns//src//data//circuit1.txt");
		circuit = circuitBuilder.getCircuit();
		
		circuit.setStartingValue("Cin", true);
		circuit.setStartingValue("A", true);
		circuit.setStartingValue("B", true);
		circuit.track("Cout");
		circuit.track("S");
		circuit.simulate();
		
		initializeView();
	}
	
	private void initializeView() {
		frame = new JFrame();
		panel = new CircuitPanel();
		
		frame.setSize(960, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Circuit simulation");
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
	
}
