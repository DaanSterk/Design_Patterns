package unit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import context_circuit.Circuit;
import context_circuit.CircuitBuilder;
import context_circuit.CircuitController;

public class CircuitSimulationTest {

	/**
	 * A test written for testing circuit 1.
	 */
	@Test
	public void testSimulationOutput() {
		CircuitController cc = new CircuitController();
		CircuitBuilder cb = cc.getCircuitBuilder();
		Circuit c = cb.getCircuit();
		int Cout = c.getOutputValue("Cout");
		int S = c.getOutputValue("S");
		assertEquals(1, Cout);
		assertEquals(0, S);
	}
}
