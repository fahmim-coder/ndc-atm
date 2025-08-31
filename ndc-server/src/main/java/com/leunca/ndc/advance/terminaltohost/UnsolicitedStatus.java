package com.leunca.ndc.advance.terminaltohost;

public final class UnsolicitedStatus extends NDCTerminalToHost {
    public UnsolicitedStatus(byte[] raw) {
        super(raw);
    }
	private final String messageClass = "1";	
	private final String messageSubClass = "2";    
	private String logicalUnitNumber;
	
}