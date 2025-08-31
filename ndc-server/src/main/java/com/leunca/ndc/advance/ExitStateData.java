package com.leunca.ndc.advance;

public class ExitStateData {
    private String Identifier;
    private String exitData;
    
    public ExitStateData(String newId) {
    	Identifier = newId;
    }       
    
    public ExitStateData(String newId, String newExitData) {
    	Identifier = newId;
    	exitData = newExitData;
    }       

    public String getIdentifier() {
        return Identifier;
    }

    public String getData() {
        return exitData;
    }
    
    public void setData(String newExitData) {
        exitData = newExitData;
    }    
}
