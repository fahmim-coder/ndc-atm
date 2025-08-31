package com.leunca.ndc.advance;

public class CustomerSelectedPIN {

    private final String Identifier = "U";
    private String CSP;
    
    public CustomerSelectedPIN(String newCSP) {
    	CSP = newCSP;
    }       

    public String getIdentifier() {
        return Identifier;
    }

    public String getCustomerSelectedPIN() {
        return CSP;
    }
}
