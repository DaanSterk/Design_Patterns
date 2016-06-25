package unit_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;

import circuit_gates.GateAND;
import circuit_gates.GateNeutral;
import context_circuit.GateFactory;
import context_circuit.gates.Gate;
import globals.GlobalVariables;

public class FactoryTest {
	
	@Before
	public void setup(){
		GlobalVariables.IS_UNIT_TESTING = true;
	}

	@Test
	public void create_NewGateAnd_CreateGateAnd_ShouldBeAnInstanceOfGateAND() {
		//Arrange
		Class<?> expectedGateType = GateAND.class;
		
		//Act
		Gate actualGate = GateFactory.create("NODE3", "GateAND");
		
		//Assert
		assertThat(actualGate, instanceOf(expectedGateType));
	}
	
	@Test
	public void create_NewGateNeutral_CreateGateNeutral_ShouldBeAnInstanceOfGateNeutral() {
		//Arrange
		Class<?> expectedGateType = GateNeutral.class;
		
		//Act
		Gate actualGate = GateFactory.create("Cout", "GateNeutral");
		
		//Assert
		assertThat(actualGate, instanceOf(expectedGateType));
	}
}
