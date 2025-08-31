package com.leunca.ndc.advance;

public class SmartCardData {

    private final String Identifier = "5";
    private String smartCardData;
    
    public SmartCardData(String newSmartCardeData) {
    	smartCardData = newSmartCardeData;
    }       

    public String getIdentifier() {
        return Identifier;
    }

    public String getSmartCardData() {
        return smartCardData;
    }
}
