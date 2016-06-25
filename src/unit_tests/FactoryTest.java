package unit_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;

import circuit_gates.*;
import context_circuit.GateFactory;
import context_circuit.gates.Gate;
import globals.GlobalVariables;

public class FactoryTest {
	
	@Before
	public void setup(){
		GlobalVariables.IS_UNIT_TESTING = true;
	}

	@Test
	public void create_NewGateAND_CreateGateAND_ShouldBeAnInstanceOfGateAND() {
		//Arrange
		Class<?> expectedGateType = GateAND.class;
		
		//Act
		Gate actualGate = GateFactory.create("NODE3", "GateAND");
		
		//Assert
		assertThat(actualGate, instanceOf(expectedGateType));
	}
	
	@Test
	public void create_NewGateNAND_CreateGateNAND_ShouldBeAnInstanceOfGateNAND() {
		//Arrange
		Class<?> expectedGateType = GateNAND.class;
		
		//Act
		Gate actualGate = GateFactory.create("NODE3", "GateNAND");
		
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
	
	@Test
	public void create_NewGateNOR_CreateGateNOR_ShouldBeAnInstanceOfGateNOR() {
		//Arrange
		Class<?> expectedGateType = GateNOR.class;
		
		//Act
		Gate actualGate = GateFactory.create("NODE3", "GateNOR");
		
		//Assert
		assertThat(actualGate, instanceOf(expectedGateType));
	}
	
	@Test
	public void create_NewGateNOT_CreateGateNOT_ShouldBeAnInstanceOfGateNOT() {
		//Arrange
		Class<?> expectedGateType = GateNOT.class;
		
		//Act
		Gate actualGate = GateFactory.create("NODE3", "GateNOT");
		
		//Assert
		assertThat(actualGate, instanceOf(expectedGateType));
	}
	
	@Test
	public void create_NewGateOR_CreateGateOR_ShouldBeAnInstanceOfGateOR() {
		//Arrange
		Class<?> expectedGateType = GateOR.class;
		
		//Act
		Gate actualGate = GateFactory.create("NODE3", "GateOR");
		
		//Assert
		assertThat(actualGate, instanceOf(expectedGateType));
	}
	
	@Test
	public void create_NewGateXOR_CreateGateXOR_ShouldBeAnInstanceOfGateXOR() {
		//Arrange
		Class<?> expectedGateType = GateXOR.class;
		
		//Act
		Gate actualGate = GateFactory.create("NODE3", "GateXOR");
		
		//Assert
		assertThat(actualGate, instanceOf(expectedGateType));
	}
}
