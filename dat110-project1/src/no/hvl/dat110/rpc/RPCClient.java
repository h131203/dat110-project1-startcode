package no.hvl.dat110.rpc;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient { 

	private MessagingClient msgclient;
	private Connection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void connect() {
		
		// TODO - START
		// connect using the underlying messaging layer connection
		
		// connect()-metoden i msgclient returner en connection, derfor connection = en connection
		
		connection = msgclient.connect();
		
		// TODO - END
	}
	
	public void disconnect() {
		
		// TODO - START
		// disconnect/close the underlying messaging connection
		
		connection.close();
		
		// TODO - END
	}
	
	public byte[] call(byte rpcid, byte[] params) {
		
		byte[] returnval = null;
		
		// TODO - START 
		
		/* 
		 * 
		Make a remote call on the RPC server by sending an RPC request message
		and receive an RPC reply message
		
		params is the marshalled parameters from the client-stub
				
		The rpcid, params, and return value must be encapsulated/decapsulated
		according to the RPC message format
			
		*/
		

		// sender melding over en opprettet connection. Ny melding opprettes, som tar parameter byte[] array (i dette tilfellet har den blitt marshalled)
		
		//innkapsle før sendes-- VIKTIG
		byte[] rpcrequest = RPCUtils.encapsulate(rpcid, params);
		connection.send(new Message(rpcrequest));
		
		//receive() metoden tar i mot RPC replyen, de-kapsler og putter innholdet inn i en message. Via Message-klassens in getData()-metode henter
		//vi ut innholdet fra melding og stter inn  i byte[] - arrayen returnval.
		byte[] rpcreply = connection.receive().getData();
		
		//UTKAPSLE
		returnval = RPCUtils.decapsulate(rpcreply);
		
		// TODO - END
		return returnval;
		
	}

}
