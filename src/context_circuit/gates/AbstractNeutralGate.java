package context_circuit.gates;

public abstract class AbstractNeutralGate {
	private InterfaceGateMediator mediator;
	
	public AbstractNeutralGate(InterfaceGateMediator m){
		mediator = m;
	}
	
	public void send(String message){
		mediator.send(message, this);
	}
	
	public InterfaceGateMediator getMediator(){
		return mediator;
	}
}
