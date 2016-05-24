package context_circuit.gates;

public abstract class AbstractGateNeutral {
	private InterfaceGateMediator mediator;
	
	public AbstractGateNeutral(InterfaceGateMediator m){
		mediator = m;
	}
	
	public void send(String message){
		mediator.send(message, this);
	}
	
	public InterfaceGateMediator getMediator(){
		return mediator;
	}
}
