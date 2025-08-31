package com.leunca.ndc.advance.hosttoterminal;

public final class EJOptionsAndTimers extends NDCHostToTerminal {
	public EJOptionsAndTimers(byte[] raw) {
	    super(raw);
	}
	
	private final String messageClass = "6";	
	private final String messageSubClass = "3";
}
