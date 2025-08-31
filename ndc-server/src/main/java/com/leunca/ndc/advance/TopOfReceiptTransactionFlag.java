package com.leunca.ndc.advance;

public enum TopOfReceiptTransactionFlag {
    WILL_NOT_PRINT_DATA_AT_THE_TOP_OF_RECEIPT('0'),
	WILL_PRINT_DATA_AT_THE_TOP_OF_RECEIPT('1');	

    private final char flag;

    private TopOfReceiptTransactionFlag(char new_flag) {
        this.flag = new_flag;
    };

    public char getFlag() {
        return flag;
    };
    
    public static TopOfReceiptTransactionFlag fromChar(char v) {
        for (TopOfReceiptTransactionFlag c:TopOfReceiptTransactionFlag.values()) {
            if (c.flag == v) {
                return c;
            }
        }
        throw new IllegalArgumentException("" + v);
    }    
}
