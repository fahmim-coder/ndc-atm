package com.leunca.ndc.advance.hosttoterminal;

public final class AcknowledgeEJUploadBlock extends NDCHostToTerminal {
	public AcknowledgeEJUploadBlock(byte[] raw) {
	    super(raw);
	}
	
	private final String messageClass = "6";	
	private final String messageSubClass = "1";
}
