package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {
		
	private byte RPCIDDISPLAY = 2;
	
	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public void write (String message) {
		
		// TODO - START
		
		// implement marshalling, call and unmarshalling for write RPC method
		
		
		byte[] params = RPCUtils.marshallString(message);
		
		byte[] reply = rpcclient.call(RPCIDDISPLAY, params); //RPC layer bes utføre metodekallet
		
		//hvilkn av disse???
		//RPCUtils.unmarshallString(reply);
		RPCUtils.unmarshallVoid(reply);
						
		// TODO - END
		
	}
}

