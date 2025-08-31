package com.leunca.ndc.advance.hosttoterminal;

public final class AcknowledgeAndStopEJ extends NDCHostToTerminal {
	public AcknowledgeAndStopEJ(byte[] raw) {
	    super(raw);
	}
	
	private final String messageClass = "6";	
	private final String messageSubClass = "2";
}
