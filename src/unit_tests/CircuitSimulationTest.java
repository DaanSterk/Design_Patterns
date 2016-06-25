package unit_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import context_circuit.Circuit;
import context_circuit.CircuitBuilder;
import context_circuit.CircuitController;
import globals.GlobalVariables;

public class CircuitSimulationTest {
	
	@Before
	public void setup(){
		GlobalVariables.IS_UNIT_TESTING = true;
	}
	
	/**
	 * A test written for testing the simulation of circuit 1.
	 * Input:
	 * A: 1
	 * B: 1
	 * Cin: 0
	 */
	@Test
	public void runSimulationCircuit1_COUT1AndS0Output_ShouldEqualCircuitCOUTAndSOutput() {
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
