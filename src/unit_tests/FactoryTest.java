package unit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;

import circuit_gates.GateAND;
import circuit_gates.GateNeutral;
import context_circuit.GateFactory;
import context_circuit.gates.Gate;

public class FactoryTest {

	@Test
	public void testCreateGateAND() {
		Gate g = GateFactory.create("NODE3", "GateAND");
		assertThat(g, instanceOf(GateAND.class));
	}
	
	@Test
	public void testCreateGateNeutral() {
		Gate g = GateFactory.create("Cout", "GateNeutral");
		assertThat(g, instanceOf(GateNeutral.class));
	}
}
