package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import context_circuit.GateFactory;
import context_circuit.gates.Gate;

public class FactoryTest {

	@Test
	public void CreateGate() {
		Gate g = GateFactory.create("GateAND", "NODE3");
		g.incrementInputCount();
		assertEquals("Creation of NODE3 was succesfull", "NODE3", g.getName());
	}

}
