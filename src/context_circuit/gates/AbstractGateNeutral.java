package context_circuit.gates;

public abstract class AbstractGateNeutral {
	private InterfaceGateMediator mediator;
//	private GateNeutral gateNeutral;
	
	public AbstractGateNeutral(InterfaceGateMediator m){
		mediator = m;
	}
	
	public void send(String message){
		if(this.getGateNeutral() == null)
			throw new NullPointerException("The outerclass of ANeutralGate cannot be null when trying to send a message.");
		else
			mediator.send(message, this.getGateNeutral());
	}
	
	public InterfaceGateMediator getMediator(){
		return mediator;
	}
	
//	public GateNeutral getGateNeutral(){
//		return gateNeutral;
//	}
//	
//	public void setGateNeutral(GateNeutral g){
//		gateNeutral = g;
//	}
	
	public GateNeutral getGateNeutral(){
		return null;
	}
}
