package com.leunca.ndc.advance.terminaltohost;

public final class SolicitedStatus extends NDCTerminalToHost {
	public SolicitedStatus(byte[] raw) {
	    super(raw);
	}
	
	private final String messageClass = "2";	
	private final String messageSubClass = "2";
	private String logicalUnitNumber;
	
}