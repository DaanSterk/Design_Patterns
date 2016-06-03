package unit_tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import context_circuit.CircuitReader;
import exceptions.CustomException;

public class CircuitReaderTest {

	@Test
	public void testNodeDescription() {
		CircuitReader circuitReader = CircuitReader.getInstance();
		circuitReader.getCircuitFromFile(getDataPath(1));
		HashMap<String, String> nodeDescriptionMap = circuitReader.getNodeDescriptionMap();
		assertEquals(nodeDescriptionMap.get("B"), "INPUT_HIGH");
		assertEquals(nodeDescriptionMap.get("NODE7"), "NOT");
		assertNotEquals(nodeDescriptionMap.get("B"), "INPUT_LOW");
	}
	
	@Test
	public void testEdgeDescription() {
		CircuitReader circuitReader = CircuitReader.getInstance();
		circuitReader.getCircuitFromFile(getDataPath(1));
		HashMap<String, List<String>> edgeDescriptionMap = circuitReader.getEdgeDescriptionMap();

		String[] actuals = {"NODE8", "NODE9"};
		
		assertArrayEquals(edgeDescriptionMap.get("NODE5").toArray(), actuals);
	}
	
	@Test(expected=CustomException.class)
	public void testIfGateExists(){
		CircuitReader circuitReader = CircuitReader.getInstance();
		circuitReader.getCircuitFromFile(getDataPath(4));
	}
	
	private String getDataPath(int id) {
		return System.getProperty("user.dir") + "//src//data//circuit" + id + ".txt";
	}

}
