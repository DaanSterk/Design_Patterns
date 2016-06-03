package unit_tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import context_circuit.CircuitReader;

public class CircuitReaderTest {

	@Test
	public void TestNodeDescription() {
		CircuitReader circuitReader = CircuitReader.getInstance();
		circuitReader.getCircuitFromFile(getDataPath());
		HashMap<String, String> nodeDescriptionMap = circuitReader.getNodeDescriptionMap();
		assertEquals(nodeDescriptionMap.get("B"), "INPUT_HIGH");
		assertEquals(nodeDescriptionMap.get("NODE7"), "NOT");
		assertNotEquals(nodeDescriptionMap.get("B"), "INPUT_LOW");
	}
	
	@Test
	public void TestEdgeDescription() {
		CircuitReader circuitReader = CircuitReader.getInstance();
		circuitReader.getCircuitFromFile(getDataPath());
		HashMap<String, List<String>> edgeDescriptionMap = circuitReader.getEdgeDescriptionMap();

		String[] actuals = {"NODE8", "NODE9"};
		
		assertArrayEquals(edgeDescriptionMap.get("NODE5").toArray(), actuals);
	}
	
	private String getDataPath() {
		return System.getProperty("user.dir") + "//src//data//circuit1.txt";
	}

}
