package unit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import context_circuit.CircuitBuilder;

public class CircuitBuilderTest {

	@Test
	public void testSimulationOutput() {
		CircuitBuilder c = new CircuitBuilder();
		c.buildCircuitFromFile(getDataPath(1));
		c.getCircuit().track("S");
		fail("Not yet implemented");
	}
	
	private String getDataPath(int id) {
		return System.getProperty("user.dir") + "//src//data//circuit" + id + ".txt";
	}
}
