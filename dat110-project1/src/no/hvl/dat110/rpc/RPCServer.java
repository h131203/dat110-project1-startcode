package no.hvl.dat110.rpc;

import java.util.HashMap;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.Connection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessageUtils;
import no.hvl.dat110.messaging.MessagingServer;

public class RPCServer {

	private MessagingServer msgserver;
	private Connection connection;
	
	// hashmap to register RPC methods which are required to extend RPCRemoteImpl	
	private HashMap<Byte,RPCRemoteImpl> services;
	
	public RPCServer(int port) {
		
		this.msgserver = new MessagingServer(port);
		this.services = new HashMap<Byte,RPCRemoteImpl>();
		
	}
	
	public void run() {
		
		// the stop RPC method is built into the server
		RPCRemoteImpl rpcstop = new RPCServerStopImpl(RPCCommon.RPIDSTOP,this);
		
		System.out.println("RPC SERVER RUN - Services: " + services.size());
			
		connection = msgserver.accept(); 
		
		System.out.println("RPC SERVER ACCEPTED");
		
		boolean stop = false;
		
		while (!stop) {
	    
		   byte rpcid = 0;
		   Message requestmsg,replymsg;
		   
		   // TODO - START
		   // - receive Message containing RPC request
		   // - find the identifier for the RPC method to invoke
		   // - lookup the method to be invoked
		   // - invoke the method
		   // - send back message containing RPC reply
			
		   
		   /* FRA GIT-oppgave teksten:
		    * After having invoked the method, any return value must be marshalled and then sent back to the client-side
		    *  in a reply message where the first byte (again) specifies the executed method. 
		    * Finally, the client-side have to unmarshall the return value (if any).
		    */
		   

		   
		   requestmsg = connection.receive();		   //receive() -metoden returnerer en message
		  // byte[] data = requestmsg.getData();
		   
		   byte[] rpqrequest = requestmsg.getData();
		   
		   
		  //henter ut verdien på første plass i tabellen som vi fikk tilsendt fra RPC klienten --> dette blir RPCid, som viser hvilken metode det kalles på
		  //System.out.println(rpqrequest.length + " rpcrequest length"); //brukt under testing - lar stå her til eget bruk seinere...
		   rpcid = rpqrequest[0];
		  // System.out.println(rpcid + " rpcid");//brukt under testing - lar stå her til eget bruk seinere...
		 		   
		   RPCRemoteImpl methodToBeInvoked = services.get(rpcid); 
		   byte[] returnvalue;
		   byte[] params;
		   byte[] rpcreply = null;
		   
		   if (methodToBeInvoked != null) {
			   params = RPCUtils.decapsulate(rpqrequest); //utkapsling
		   //replymsg = new Message(methodToBeInvoked.invoke(params)); //kjører metode (unmarshall og marshalling skjer her)
		   
			   returnvalue = methodToBeInvoked.invoke(params);
			   rpcreply =  RPCUtils.encapsulate(rpcid, returnvalue);
			
		   }
		   else {
			   rpcreply = new byte[0];
			   System.out.println("feil");
		   }
		   replymsg = new Message(rpcreply);
			connection.send(replymsg);
		   
		   
		   //innkapsle returverdi og sende message
		   
		  
		   
		  
		  /* 
		   if (methodToBeInvoked != null) {
			   //replymsg = new Message(methodToBeInvoked.invoke(requestmsg.getData()));  
			   replymsg = new Message(methodToBeInvoked.invoke(data));
			   
			   //begge over skal funke?
			   
			   connection.send(replymsg); //tar seg av encapsulation
		   }
		   */
		   
		   // TODO - END
		   
		   if (rpcid == RPCCommon.RPIDSTOP) {
			   stop = true;
		   }
		}
	
	}
	
	// used by server side implementation to register themselves in the RPC server
	public void register(byte rpcid, RPCRemoteImpl impl) {
		services.put(rpcid, impl);
	}
	
	public void stop() {
		connection.close();
		msgserver.stop();
		
	}
}
