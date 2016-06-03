package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;

import circuit_gates.GateAND;
import circuit_gates.GateNeutral;
import context_circuit.GateFactory;
import context_circuit.gates.Gate;

public class FactoryTest {

	@Test
	public void CreateGateAND() {
		Gate g = GateFactory.create("NODE3", "GateAND");
		assertThat(g, instanceOf(GateAND.class));
	}
	
	@Test
	public void CreateGateNeutral() {
		Gate g = GateFactory.create("NODE3", "GateNeutral");
		assertThat(g, instanceOf(GateNeutral.class));
	}

}
