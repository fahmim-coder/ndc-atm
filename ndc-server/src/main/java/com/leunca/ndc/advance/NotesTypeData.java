package com.leunca.ndc.advance;

public class NotesTypeData {

	private String identifier;
		
    protected String noteTypeIdentifier;             // For escrow notes = identifier = "w", Two‐digit hexadecimal number (01 ‐ 32) representing a note type, allowing up to 50 note types to be represented.
                                                     // For ECB6 suspect notes = identifier = "c", Note type identifier as a hexadecimal number in the range ‘0001’ t0 ‘00FF’   
                                                     // For counterfeit notes = identifier = "d", Note type identifier. The hexadecimal identifier of the note type in the range ‘0001’ to ‘00FF’
    protected int numberOfNotesOfTheType;            
    
    public NotesTypeData(String Id) {
    	identifier = Id;
    }
}
