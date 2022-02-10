package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCClientStopStub;
import no.hvl.dat110.rpc.RPCRemoteImpl;
import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.system.display.DisplayImpl;
import no.hvl.dat110.system.sensor.SensorDevice;
import no.hvl.dat110.system.sensor.SensorImpl;

public class Controller  {
	
	private static int N = 5;
	
	public static void main (String[] args) {
		
		DisplayStub display;
		SensorStub sensor;
		
		RPCClient displayclient,sensorclient;
		
		System.out.println("Controller starting ...");
				
		// create RPC clients for the system
		displayclient = new RPCClient(Common.DISPLAYHOST,Common.DISPLAYPORT);
		sensorclient = new RPCClient(Common.SENSORHOST,Common.SENSORPORT);
		
		// setup stop methods in the RPC middleware
		RPCClientStopStub stopdisplay = new RPCClientStopStub(displayclient);
		RPCClientStopStub stopsensor = new RPCClientStopStub(sensorclient);
				
		// TODO - START
		
		// create local display and sensor stub objects
		// connect to sensor and display RPC servers
		
	
		display = new DisplayStub(displayclient);
		sensor = new SensorStub(sensorclient);

		displayclient.connect();
		sensorclient.connect();	
		

		for (int i = 0; i < N; i++) {
		
			int temp = sensor.read(); //henter temp fra sensor		
			display.write(Integer.toString(temp)); //skriver temp til displayet
			
			//sikrer ny verdi for hver løkke
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		// TODO - END
		
		stopdisplay.stop();
		stopsensor.stop();
	
		displayclient.disconnect();
		sensorclient.disconnect();
		
		System.out.println("Controller stopping ...");
		
	}
}
