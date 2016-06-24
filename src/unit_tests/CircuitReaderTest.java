package unit_tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import context_circuit.CircuitReader;
import exceptions.CustomException;

public class CircuitReaderTest {

	@Test
	public void testNodeDescriptionCircuitOne_SetTheDescription_GetTheDescription_ShouldEqualDescription() {
		//Arrange
		String expectedBDescription = "INPUT_HIGH";
		String expectedNODE7Description = "NOT";
		String incorrectExpectedBDescription = "INPUT_LOW";
		
		//Act
		CircuitReader circuitReader = CircuitReader.getInstance();
		circuitReader.getCircuitFromFile(getDataPath(1));
		HashMap<String, String> nodeDescriptionMap = circuitReader.getNodeDescriptionMap();
		String actualBDescription = nodeDescriptionMap.get("B");
		String actualNODE7Description = nodeDescriptionMap.get("NODE7");
		
		//Assert
		assertEquals(actualBDescription, expectedBDescription);
		assertEquals(actualNODE7Description, expectedNODE7Description);
		assertNotEquals(actualBDescription, incorrectExpectedBDescription);
	}
	
	@Test
	public void testEdgeDescriptionCircuitOne_SetEdgeDescriptionOfGate_GetEdgeDescription_ShouldEqualEdgeDescription() {
		//Arrange
		String[] expectedNODE5EdgeDescriptions = {"NODE8", "NODE9"};
		
		//Act
		CircuitReader circuitReader = CircuitReader.getInstance();
		circuitReader.getCircuitFromFile(getDataPath(1));
		HashMap<String, List<String>> edgeDescriptionMap = circuitReader.getEdgeDescriptionMap();
		String[] actualNODE5EdgeDescription = edgeDescriptionMap.get("NODE5").toArray(new String[0]);
		
		//Assert
		assertArrayEquals(actualNODE5EdgeDescription, expectedNODE5EdgeDescriptions);
	}
	
	private String getDataPath(int id) {
		return System.getProperty("user.dir") + "//src//data//circuit" + id + ".txt";
	}

}
