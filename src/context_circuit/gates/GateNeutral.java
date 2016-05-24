package context_circuit.gates;

import java.util.ArrayList;

public abstract class GateNeutral extends Gate implements InterfaceGateMediator {
	private ANeutralGate privateNeutralGate;
	private ArrayList<GateNeutral> neutralGates;
	
	public GateNeutral(InterfaceGateMediator mediator){
		neutralGates = new ArrayList<GateNeutral>();
		privateNeutralGate = new ANeutralGate(mediator);
	}
	
	@Override
	protected void applyLogic() {
		boolean outputValue = inputValues.get(0);
		emit(outputValue);
	}
	
	public void addNeutralGate(GateNeutral g){
		neutralGates.add(g);
	}
	
	public void send(String message, GateNeutral originator){
		//let all other GateNeutrals know that this GateNeutral has changed
	    for(GateNeutral neutralGate: neutralGates) {
	      //don't tell ourselves
	      if(neutralGate != originator) {
	    	  neutralGate.receive(message);
	      }
	    }
	}
	
	public void receive(String message){
		System.out.println("This is the message that was received:" + message);
	}
	
	private class ANeutralGate extends AbstractNeutralGate{

		public ANeutralGate(InterfaceGateMediator m) {
			super(m);
		}
		
	}
}


