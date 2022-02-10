package no.hvl.dat110.messaging;

import no.hvl.dat110.TODO;

public class Message {

	private byte[] data;
	/*
	 * @param byte[] data
	 */
	
	public Message(byte[] data) {
		
		if (data != null && data.length < MessageConfig.SEGMENTSIZE) {
			this.data = data;
		}
		
	}
	

	public byte[] getData() {
		return this.data; 
	}

}
