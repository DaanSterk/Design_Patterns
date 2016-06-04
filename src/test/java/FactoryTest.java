package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import context_circuit.GateFactory;
import context_circuit.gates.Gate;

public class FactoryTest {
	@Test
	public void test() {
		GateFactory factory = new GateFactory();
		
		String type = "GateXOR";
		String name = "Gate1";
		Gate gate = factory.create(type, name);
		assertEquals(gate.getType(), type);
	}
}
