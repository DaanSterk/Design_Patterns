package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import context_circuit.gates.Gate;

public class CircuitPanel extends JPanel {
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(100, 100, 100, 20);
		g.drawString("node1 (XOR)", 105, 115);
	}
	
}
