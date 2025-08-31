package com.leunca.ndc.advance.hosttoterminal;

public final class EncryptionKeyChange extends NDCHostToTerminal {
	public EncryptionKeyChange(byte[] raw) {
	    super(raw);
	}
	
	private final String messageClass = "3";	
	private final String messageSubClass = "3";
}
