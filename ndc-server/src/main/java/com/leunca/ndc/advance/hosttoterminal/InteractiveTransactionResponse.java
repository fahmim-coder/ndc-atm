package com.leunca.ndc.advance.hosttoterminal;

public final class InteractiveTransactionResponse extends NDCHostToTerminal {
	public InteractiveTransactionResponse(byte[] raw) {
	    super(raw);
	}
	
	private final String messageClass = "3";	
	private final String messageSubClass = "2";
}
