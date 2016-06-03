package context_circuit;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import view.CircuitPanel;

public class CircuitController {

	private Circuit circuit;
	private CircuitBuilder circuitBuilder;
	
	// VIEW
	private JFrame frame;
	private CircuitPanel panel;
	
	public CircuitController() {
		initializeView();
		start();
	}
	
	private void initializeView() {
		frame = new JFrame();
		panel = new CircuitPanel(frame, this);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE); // To fix scroll repaint issues.
		frame.setContentPane(scrollPane);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Circuit simulation");
		frame.setSize(1920, 400);
	}
	
	private void start() {
		circuitBuilder = new CircuitBuilder();
		circuitBuilder.passController(this);
		circuitBuilder.buildCircuitFromFile(getDataPath());
		circuit = circuitBuilder.getCircuit();
		
		panel.setPreferredSize(new Dimension(circuit.getGateAmount() * panel.getGateWidth(), 400));
		frame.setVisible(true);
		
		// Configuration
		circuit.setDelay(100);
		circuit.track("S");
		circuit.track("Cout");
		
		circuit.simulate();
	}
	
	public CircuitPanel getView() {
		return panel;
	}
	
	private String getDataPath() {
		return System.getProperty("user.dir") + "//src//data//circuit1.txt";
	}
	
}
