package com.leunca.ndc.advance.hosttoterminal;

public final class TerminalCommands extends NDCHostToTerminal {
	public TerminalCommands(byte[] raw) {
	    super(raw);
	}
	
	private final String messageClass = "1";	
}

