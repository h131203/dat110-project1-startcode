package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class SensorStub extends RPCLocalStub {

	private byte RPCIDREAD = 1;
	
	public SensorStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public int read() {
		
		int temp = 0;
		
		// TODO - START
		// implement marshalling, call and unmarshalling for read RPC method
		
		byte[] request = RPCUtils.marshallInteger(RPCIDREAD);
		//byte[] request = RPCUtils.marshallVoid(RPCIDREAD);
		
		byte[] reply = rpcclient.call(RPCIDREAD, request);
		
		temp = RPCUtils.unmarshallInteger(reply);
		
		// TODO - END
		
		return temp;
	}
	
}
