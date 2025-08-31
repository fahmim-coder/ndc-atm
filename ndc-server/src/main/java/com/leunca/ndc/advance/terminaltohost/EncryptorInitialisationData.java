package com.leunca.ndc.advance.terminaltohost;

public final class EncryptorInitialisationData extends NDCTerminalToHost {
	public EncryptorInitialisationData(byte[] raw) {
	    super(raw);
	}
	
	private final String messageClass = "2";	
	private final String messageSubClass = "3";
	private String logicalUnitNumber;
}
