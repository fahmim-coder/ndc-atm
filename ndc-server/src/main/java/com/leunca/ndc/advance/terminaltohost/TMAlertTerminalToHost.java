package com.leunca.ndc.advance.terminaltohost;

public final class TMAlertTerminalToHost extends NDCTerminalToHost {
	public TMAlertTerminalToHost(byte[] raw) {
	    super(raw);
	}

	private final String messageClass = "3";	
}