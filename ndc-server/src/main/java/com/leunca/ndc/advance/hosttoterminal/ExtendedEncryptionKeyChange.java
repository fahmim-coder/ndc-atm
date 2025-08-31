package com.leunca.ndc.advance.hosttoterminal;

public final class ExtendedEncryptionKeyChange extends NDCHostToTerminal {
	public ExtendedEncryptionKeyChange(byte[] raw) {
	    super(raw);
	}
	
	private final String messageClass = "3";	
	private final String messageSubClass = "4";
}
