package context_circuit;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
		circuitBuilder.buildCircuitFromFile("E://Users//Daan//workspace//Design_Patterns//src//data//CIRCUIT3.txt");
		circuit = circuitBuilder.getCircuit();
		
		// Configuration
//		circuit.track("Cout");
		circuit.track("F");
		circuit.setDelay(10);
		
		circuit.simulate();
	}
	
	private void initializeView() {
		frame = new JFrame();
		panel = new CircuitPanel(frame);
		
//		frame.setContentPane(panel);
		
		panel.setPreferredSize(new Dimension(100000, 400));
		frame.setContentPane(new JScrollPane(panel));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Circuit simulation");
		frame.setSize(1920, 400);
		frame.setVisible(true);
	}
	public CircuitPanel getView() {
		return panel;
	}
	
}
