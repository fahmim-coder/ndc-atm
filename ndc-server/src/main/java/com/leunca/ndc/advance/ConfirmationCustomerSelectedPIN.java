package com.leunca.ndc.advance;

public class ConfirmationCustomerSelectedPIN {
    private final String Identifier = "V";
    private String confirmationCSP;
    
    public ConfirmationCustomerSelectedPIN(String newConfirmationCSP) {
    	confirmationCSP = newConfirmationCSP;
    }       

    public String getIdentifier() {
        return Identifier;
    }

    public String getConfirmationCustomerSelectedPIN() {
        return confirmationCSP;
    }
}
