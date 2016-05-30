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
		initializeView();
		
		circuitBuilder = new CircuitBuilder();
		circuitBuilder.passController(this);
		circuitBuilder.buildCircuitFromFile("E://Users//Daan//workspace//Design_Patterns//src//data//circuit1.txt");
		panel.repaint();
		
		circuit = circuitBuilder.getCircuit();
		
		circuit.setStartingValue("Cin", true);
		circuit.setStartingValue("A", true);
		circuit.setStartingValue("B", true);
		circuit.track("Cout");
		circuit.track("S");
		circuit.setDelay(1000);
		circuit.simulate();
	}
	
	public void drawGate(String name) {
		
	}
	
	private void initializeView() {
		frame = new JFrame();
		panel = new CircuitPanel(frame);
		
		frame.setSize(1920, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Circuit simulation");
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
	public CircuitPanel getView() {
		return panel;
	}
	
}
