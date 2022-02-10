package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;

import no.hvl.dat110.TODO;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		
		byte[] rpcmsg = null;
		
		// TODO - START
		
		// Encapsulate the rpcid and payload in a byte array according to the  RPC message syntax
		
		/*
		 * payload+1 fordi rpcID (som viser hvilken metoden som skal kjøres) tar første plass i tabellen
		 * setter plass [0] i byte-arrayen til å være den ID som kommer inn
		 * Deretter flyttes data fra paylod sin første plass og over til rpcmsg[]-arrayen fra plass nr 1 (dvs plassen etter rpc IDen)
		 * @return rpcmsg, som er en byte[] array
		 */
		
		rpcmsg = new byte[payload.length+1];
		
		rpcmsg[0] = rpcid;
		
		for (int i=0; i <payload.length; i++) {
			rpcmsg[i+1] = payload[i];
		}
		
		// TODO - END
		
		return rpcmsg;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {
		
		byte[] payload = null;
		
		// TODO - START
		
		// Decapsulate the rpcid and payload in a byte array according to the  RPC message syntax
		
		
		/*
		 * payload.length-1= dette fordi første plass indikerer hvilken metode det er, så den trenger vi ikke nå.
		 * Den er ikke en del av selve payloaden.
		 * 
		 * I løkken starter vi på plass plass nr 2(altså 1) i rpcmsg, da plass 0/1 er rpcID / metodenummeret
		 */
		payload = new byte[rpcmsg.length-1];
		
		for (int i=0; i<payload.length; i++) {
			payload[i] = rpcmsg[i+1];
		}
		
		// TODO - END
		
		return payload;
		
	}
	
	public static byte[] marshallString(String str) {
		
		/*
		 * VIKTIG I MARSHALLING: få input-dataen omgjort til bytes, på et vis. 
		 */
		
		byte[] encoded = null;
		
		// TODO - START 
		/*
		 * +1 i length for å lage plass til [0] der metoden sin id (RPCID) skal stå?
		 * 
		 * getBytes() metoden - VIKTIG 
		 * 	metoden over gir ut stringen som en sekvens av bytes, lagre i en  ny byte-tabell. Det er lengden på den tabellen vi bruker +1
		 * Deretter settes det sekvens av bytes inn i encoded-tabellen, fra plass 1 (etter RPCid)
		 */

		encoded = new byte[str.getBytes().length];
		
			
		for (int i=0; i<str.getBytes().length; i++) {
			encoded[i] = str.getBytes()[i];
		}

		
		// TODO - END
		
		return encoded;
	}
	
	public static String unmarshallString(byte[] data) {
		
		String decoded = null; 
		
		// TODO - START 
		
		/*
		 * Stringen under lages fra en dekoding av byte-arrayen som sendes med. Den starter på plass 1, dvs første plass etter RPCic (metode-Id)
		 */
		
		
		decoded = new String(data,0,data.length);
		
		
		// TODO - END
		
		return decoded;
	}
	
	public static byte[] marshallVoid() {
		
		byte[] encoded = null;
		
		// TODO - START 
		
		encoded = new byte[0]; 
		
		
				
		// TODO - END
		
		return encoded;
		
	}
	
	public static void unmarshallVoid(byte[] data) {
		
		// TODO
		
		return;
		
	}
	
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}
	
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}
	
	public static byte[] marshallInteger(int x) {
		
		byte[] encoded = null;
		
		// TODO - START 

		/*
		 * gange med 4 ? dele på 4?
		 * Må se litt mer på denne 
		 * 
		 * int er 4 bytes
		 */
		
		//encoded = new byte[(x/4)+1];
		encoded = new byte[5];
	
		
//		for (int i = 0; i < 4; i++) {
//			encoded[i+1] = (byte) (x >> (i*8));
//		}
//		
		
		byte[] b = ByteBuffer.allocate(4).putInt(x).array();
		
		for(int i = 1; i<encoded.length; i++) {
			encoded[i] = b[i-1];
		}
		// TODO - END
		
		return encoded;
	}
	
	
	public static int unmarshallInteger(byte[] data) {
		
		int decoded = 0;
		
		// TODO - START 
		
		/*
		 * int er 4 bytes
		 * 
		 * Må se litt mer på denne for å faktisk skjønne den..
		 */
		
//		for (int i=0; i<4; i++) {
//			decoded += Byte.toUnsignedInt((byte) (data[i+1] << (i*8)));
//		}
		decoded = ByteBuffer.wrap(data, 1, 4).getInt();
		
		// TODO - END
		
		return decoded;
		
	}
}
