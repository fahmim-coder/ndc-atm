package com.leunca.ndc.advance.hosttoterminal;

public final class TransactionReply extends NDCHostToTerminal {
	public TransactionReply(byte[] raw) {
	    super(raw);
	}
	
	public String getMessageClass() {
		return messageClass;
	}

	private final String messageClass = "4";
	private String logicalUnitNumber;
}

