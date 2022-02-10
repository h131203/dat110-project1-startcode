package no.hvl.dat110.system.display;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.system.controller.Common;
import no.hvl.dat110.system.sensor.SensorImpl;


public class DisplayDevice {
	
	public static void main(String[] args) {
		
		System.out.println("Display server starting ...");
		
		// TODO - START
		// implement the operation of the display RPC server
		// see how this is done for the sensor RPC server in SensorDevice
				
		RPCServer displayserver = new RPCServer(Common.DISPLAYPORT); //viktig at det er riktig port her!
		
		
		DisplayImpl display = new DisplayImpl((byte)2, displayserver); 
		
//		displayserver.register((byte)2, display); trengs ikke likevel da det gjøres et annen plass
				
		displayserver.run();
		displayserver.stop();
				
		// TODO - END
		
		System.out.println("Display server stopping ...");
		
	}
}
