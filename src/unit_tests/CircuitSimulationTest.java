package unit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import context_circuit.Circuit;
import context_circuit.CircuitBuilder;
import context_circuit.CircuitController;

public class CircuitSimulationTest {

	/**
	 * A test written for testing circuit 1.
	 * Parameters:
	 * A: 1
	 * B: 1
	 * Cin: 0
	 */
	@Test
	public void runSimulationCircuit1WithAAndB1_COUTAndSOutput_ShouldEqualCircuitCOUTAndSOutput() {
		//Arrange
		int expectedCoutOutput = 1;
		int expectedSOutput = 0;
		
		//Act
		CircuitController cc = new CircuitController();
		CircuitBuilder cb = cc.getCircuitBuilder();
		Circuit c = cb.getCircuit();
		int ActualCoutOutput = c.getOutputValue("Cout");
		int ActualSOutput = c.getOutputValue("S");
		
		//Assert
		assertEquals(expectedCoutOutput, ActualCoutOutput);
		assertEquals(expectedSOutput, ActualSOutput);
	}
}
