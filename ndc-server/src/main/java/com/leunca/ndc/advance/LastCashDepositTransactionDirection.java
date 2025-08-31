package com.leunca.ndc.advance;

public enum LastCashDepositTransactionDirection {

    NOT_BNA_DEPOSIT('0'),
    VAULT_DIRECTION('1'),
    REFUND_DIRECTION('2');
	
	private final char value;

	LastCashDepositTransactionDirection(char v) {
        value = v;
    }

    public char value() {
        return value;
    }
    
    public static LastCashDepositTransactionDirection fromChar(char v) {
        for (LastCashDepositTransactionDirection c: LastCashDepositTransactionDirection.values()) {
            if (c.value == v) {
                return c;
            }
        }
        throw new IllegalArgumentException("" + v);
    }


}
