package com.leunca.ndc.advance;

public enum LastStatusIssued {

	NONE_SENT('0'),
    GOOD_TERMINATION_SENT('1'),
    ERROR_STATUS_SENT('2'),
    TRANSACTION_REPLY_REJECTED('3');	
    
	private final char value;

    LastStatusIssued(char v) {
        value = v;
    }    

    public char getLastStatusIssuedType() {
        return value;
    }

    public static LastStatusIssued fromChar(char v) {
        for (LastStatusIssued c: LastStatusIssued.values()) {
            if (c.value == v) {
                return c;
            }
        }
        throw new IllegalArgumentException("" + v);
    }
}
