package com.leunca.ndc.advance.terminaltohost;

public final class UploadEJData extends NDCTerminalToHost {
	public UploadEJData(byte[] raw) {
	    super(raw);
	}
	private final String messageClass = "6";	
	private final String messageSubClass = "1";    
	
}