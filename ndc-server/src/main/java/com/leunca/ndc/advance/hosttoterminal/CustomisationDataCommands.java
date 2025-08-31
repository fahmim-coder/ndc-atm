package com.leunca.ndc.advance.hosttoterminal;

public final class CustomisationDataCommands extends NDCHostToTerminal {
	public CustomisationDataCommands(byte[] raw) {
	    super(raw);
	}
	
	private final String messageClass = "3";	
	private final String messageSubClass = "1";
}
