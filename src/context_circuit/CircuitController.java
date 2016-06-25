package context_circuit;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import globals.GlobalVariables;
import view.CircuitPanel;
import view.ConsoleInteractor;

public class CircuitController {

	private Circuit circuit;
	private CircuitBuilder circuitBuilder;
	
	// VIEW
	private JFrame frame;
	private CircuitPanel panel;
	
	public CircuitController() {
		if(!GlobalVariables.IS_UNIT_TESTING)
			initializeView();
		start();
	}
	
	private void initializeView() {
		frame = new JFrame();
		panel = new CircuitPanel(frame);
		
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
		
		if(!GlobalVariables.IS_UNIT_TESTING){
			panel.setPreferredSize(new Dimension(circuit.getGateAmount() * panel.getGateWidth(), 400));
			frame.setVisible(true);
			readInput();
		}
		
		// Configuration
		circuit.setDelay(15);
//		circuit.track("S");
//		circuit.track("Cout");

		circuit.simulate();
		
		restart();
	}
	
	public CircuitPanel getView() {
		return panel;
	}
	
	public CircuitBuilder getCircuitBuilder(){
		return circuitBuilder;
	}
	
	private String getDataPath() {
		return System.getProperty("user.dir") + "//src//data//circuit1.txt";
	}
	
	private void readInput(){
		ConsoleInteractor ci = new ConsoleInteractor();
		System.out.println("Enter a command (case sensitive): 1: (gateName) (startingValue), 2: start");
		while (!ci.isClosed()) {
			String[] input = ci.command();
			if (input != null) {
				circuit.setStartingValue(input);
			}
		}
	}
	
	private void restart(){
		if(!GlobalVariables.IS_UNIT_TESTING){
			int option = restartDialog();
			
			if(JOptionPane.OK_OPTION == option){
				frame.setVisible(false);
				frame.dispose();
				initializeView();
				start();
			}
		}
	}
	
	private int restartDialog(){
		Object[] options = { "RESTART", "CANCEL" };
		return JOptionPane.showOptionDialog(null, "Click RESTART to restart the circuit.", "Do you want to restart the circuit?",
		             JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
		             null, options, options[0]);
	}
}
