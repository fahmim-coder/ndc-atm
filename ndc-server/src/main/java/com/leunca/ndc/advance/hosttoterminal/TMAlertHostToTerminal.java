package com.leunca.ndc.advance.hosttoterminal;

public final class TMAlertHostToTerminal extends NDCHostToTerminal {
	public TMAlertHostToTerminal(byte[] raw) {
	    super(raw);
	}
	
	private final String messageClass = "5";	
}

