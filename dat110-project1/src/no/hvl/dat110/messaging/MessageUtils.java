package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static final int MESSAGINGPORT = 8080;
	public static final String MESSAGINGHOST = "localhost";
	
	public static byte[] encapsulate(Message message) {
		
		byte[] segment = null;
		byte[] data;
		
		// TODO - START
		
		// encapulate/encode the payload data of the message and form a segment
		// according to the segment format for the messagin layer
		
		data = message.getData(); //dataen som ligger i messagen. Henter det ut via metoden og 
		
		segment = new byte[MessageConfig.SEGMENTSIZE];
		segment[0] = (byte) data.length; //plass 0: lengden/størrelsen på dataen (dette iht syntax)
		
		for (int i = 1; i<= data.length; i++) {
			segment[i] = data[i-1];
		}
		

		// TODO - END
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {

		Message message = null;
		
		// TODO - START
		// decapsulate segment and put received data into a message
		
		
		byte[] data = new byte[segment[0]]; //lengde lik mengden data i segment, som er spesifisert på plass 0

		
		//henter ut lengden i payloaden/meldingen
		//Husk det er kun det som hentes ut av segmentet som skal inn i meldingen -> dvs, ikke første palss i arrayen
		
		
		//Flytter data fra segmentet som kommer inn og over til data-arrayen
		for (int i = 0; i<segment[0]; i++) {
			data[i] = segment[i+1];
		}
		
		//oppretter ny melding som tar input (byte[] tabell), der tabellen inneholder dataen vi har hentet fra segmentet.
		//det handler om å flytte data fra segment og over til melding?
		message = new Message(data);		
		// TODO - END
		
		return message;
		
	}
	
}
