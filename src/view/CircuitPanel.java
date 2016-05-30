package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import context_circuit.gates.Gate;

public class CircuitPanel extends JPanel {
	
	private JFrame parentFrame;
	
	private int gateWidth;
	private int gateHeight;
	
	private int marginStringLeft;	// Left margin of string
	private int marginStringTop;	// Top margin of string
	private int marginAllTop;		// Top margin of circuit
	private int marginConnectedTop; // Top margin of output gates
	
	private HashMap<String, Gate> gates;
	
	private String currOutputGateName;
	private boolean currOutputValue;
	
	public CircuitPanel(JFrame parent) {
		parentFrame = parent;
		
		gateWidth = 75;
		gateHeight = 50;
		
		marginStringTop = 15;
		marginStringLeft = 5;
		marginAllTop = 0;
		marginConnectedTop = 2;
		
		gates = new HashMap<String, Gate>();
	}
	
	public void drawGate(Gate g) {
		gates.put(g.getName(), g);
	}
	
	public void showOutput(String gateName, boolean value) {
		currOutputGateName = gateName;
		currOutputValue = value;
		repaint();
		parentFrame.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int outerCounter = 0;
		for (String name : gates.keySet()) {
			Gate gate = gates.get(name);
			g.setColor(Color.RED);
			g.drawRect(gateWidth * outerCounter, marginAllTop, gateWidth, gateHeight);
			g.drawString(gate.getName(), gateWidth * outerCounter + marginStringLeft, marginStringTop + marginAllTop);
			g.drawString("(" + gate.getType() + ")", gateWidth * outerCounter + marginStringLeft, marginStringTop * 2 + marginAllTop);
			if (name.equals(currOutputGateName)) {
				String binaryValue;
				if (currOutputValue) {
					binaryValue = "0";
				}
				else {
					binaryValue = "1";
				}
				g.drawString(binaryValue, gateWidth * outerCounter + marginStringLeft, marginStringTop * 3 + marginAllTop);
			}
			
			ArrayList<Gate> outputGates = gate.getOutputGates();
			int innerCounter = 1;
			for (Gate h : outputGates) {
				g.setColor(Color.BLUE);
				g.drawRect(gateWidth * outerCounter, marginAllTop + gateHeight * innerCounter + marginConnectedTop, gateWidth, gateHeight);
				g.drawString(h.getName(), gateWidth * outerCounter + marginStringLeft, marginAllTop + gateHeight * innerCounter + marginStringTop + marginConnectedTop);
				g.drawString("(" + h.getType() + ")", gateWidth * outerCounter + marginStringLeft, marginAllTop + gateHeight * innerCounter + marginStringTop * 2 + marginConnectedTop);
				innerCounter++;
			}
			
			outerCounter++;
		}
	}
	
}
