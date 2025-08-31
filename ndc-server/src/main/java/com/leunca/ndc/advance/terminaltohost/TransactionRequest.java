package com.leunca.ndc.advance.terminaltohost;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

import io.netty.buffer.ByteBuf;

import com.leunca.ndc.NDCField;
import com.leunca.ndc.NDCParameter;
import com.leunca.ndc.advance.BarcodeData;
import com.leunca.ndc.advance.BunchChequeDepositType;
import com.leunca.ndc.advance.CoinDispensedFromHopperType;
import com.leunca.ndc.advance.ConfirmationCustomerSelectedPIN;
import com.leunca.ndc.advance.CounterfeitNotesTypeData;
import com.leunca.ndc.advance.CustomerSelectedPIN;
import com.leunca.ndc.advance.EscrowNotesTypeData;
import com.leunca.ndc.advance.ExitStateData;
import com.leunca.ndc.advance.LastTransactionStatusDataType;
import com.leunca.ndc.advance.SingleChequeDeposit;
import com.leunca.ndc.advance.SmartCardData;
import com.leunca.ndc.advance.SuspectNotesTypeData;
import com.leunca.ndc.advance.TopOfReceiptTransactionFlag;
import com.leunca.ndc.advance.Track1Data;
import com.leunca.ndc.advance.VoiceGuidanceType;

public final class TransactionRequest extends NDCTerminalToHost {
	public TransactionRequest(ByteBuf raw) {
        super(raw);
    }
    
	private final String messageClass = "1";	
	private final String messageSubClass = "1";
	private String logicalUnitNumber;
	private String timeVariantNumber;
	private TopOfReceiptTransactionFlag TopOfReceiptTransactionFlag;
	private String messageCoordinatorNumber;
	private String track2Data;
	private String track3Data;
	private String operationCodeData;
	private String amountEntry;
	private String pinBufferA;
	private String generalPurposeBufferB;
	private String generalPurposeBufferC;
	private Track1Data track1Data;
	private LastTransactionStatusDataType lastTransactionStatusData;
	private CustomerSelectedPIN customerSelectedPIN;
    private ConfirmationCustomerSelectedPIN confirmationCustomerSelectedPIN;
	private ExitStateData vcDataW = new ExitStateData("W");
	private ExitStateData vcDataX = new ExitStateData("X");
	private ExitStateData vcDataY = new ExitStateData("Y");
	private ExitStateData vcDataZ = new ExitStateData("Z");
	private ExitStateData vcDataBracket = new ExitStateData("[");
	private ExitStateData vcDataBackSlash = new ExitStateData("\\");
	private SmartCardData smartCardData;
	private SingleChequeDeposit singleChequeDeposit;
	private List<EscrowNotesTypeData> escrowNotes;
	private List<SuspectNotesTypeData> suspectNotes;
	private List<CounterfeitNotesTypeData> counterfeitNotes;
	private BarcodeData barcodeSData;
	private CoinDispensedFromHopperType coinDispensedFromHopperType;
	private BunchChequeDepositType bunchChequeDeposit;
    private VoiceGuidanceType voiceGuidance;	
    private String MessageAuthenticationCode;
    
    private ByteBuf getFS(ByteBuf message){
    	int next = message.bytesBefore(NDCParameter.FIELD_SEPARATOR);
    	if (next >= 0) { 
    		message.readerIndex(message.readerIndex() + next);
    	    return message.readBytes(1);
    	} else {
    		throw new NoSuchElementException("Field separator not found in the array");
    	}    
    }
        
    private ByteBuf getUntilNextFS(ByteBuf message){
    	int next = message.bytesBefore(NDCParameter.FIELD_SEPARATOR);
    	if (next >= 0) { 
    	    return message.readBytes(next);
    	} else {
    		throw new NoSuchElementException("Next field separator not found in the array");
    	}    
    }
    
    private ByteBuf getUntilNextFS(ByteBuf message, int length){
    	int next = message.bytesBefore(NDCParameter.FIELD_SEPARATOR);
    	if ((next >= 0)&&(next <= length)) { 
    	    return message.readBytes(next);
    	} else if (next > length) {
    		throw new NoSuchElementException("Data exceeds expected length. Expected length = " + length + ", actual length = " + next);    		
    	} else {
    		throw new NoSuchElementException("Field separator not found in the array");
    	}    
    }
    
    private int getHeaderLength(){ 
    	return NDCParameter.messageHeaderLength; 
    };
    
    private int getLogicalUnitNumberLength(){ return 0; };
    private int getAmountEntryLength(){ return 0; };
    
    private boolean isMandatoryGroupWith(String[] fieldids) { return true; };
    private boolean isMandatorySetAtTRS(String fieldId) {  return true; };
    private boolean isMandatoryDependOnECP(int ECPNum){ return true; };
    private boolean isTimeVariantMandatory(){ return true; };
    private boolean isMACRequired(){ return true; };
    
    public void parse(ByteBuf rawMessage)  {
        try {
        	rawMessage.readerIndex(0);            
            fields.add(new NDCField("a",   rawMessage.readBytes(getHeaderLength()),            true  ));                                            // Header ‐ Protocol‐dependent.
            fields.add(new NDCField("b",   rawMessage.readBytes(1),                            true  ));     
            fields.add(new NDCField("c",   rawMessage.readBytes(1),                            true  )); 
            fields.add(new NDCField("FS",  getFS(rawMessage),                                  true  )); 
            fields.add(new NDCField("d",   rawMessage.readBytes(getLogicalUnitNumberLength()), true  ));                                             // 3 or 9        
            fields.add(new NDCField("FS",  getFS(rawMessage),                                  true  )); 
            fields.add(new NDCField("FS",  getFS(rawMessage),                                  true  )); 
            fields.add(new NDCField("e",   rawMessage.readBytes(8),                            isTimeVariantMandatory()));                           // *See* Table Note 3                         
            fields.add(new NDCField("FS",  getFS(rawMessage),                                  true  )); 
            fields.add(new NDCField("f",   rawMessage.readBytes(1),                            true  )); 		
            fields.add(new NDCField("g",   rawMessage.readBytes(1),                            true  ));                                             // *See* Table Note 1
            fields.add(new NDCField("FS",  getFS(rawMessage),                                  true  ));    
            fields.add(new NDCField("h",   getUntilNextFS(rawMessage, 39),                     false ));                                             // Var (39) | *See* Table Note 1 | Table Note 1: Fields ‘h’ to ‘n’ are optional, and the fields to be included in the message are specified in the Transaction Request state. If no keys have been loaded into the encryptor, field ‘l’ is not sent.        		
            fields.add(new NDCField("FS",  rawMessage.readBytes(1),                            true  ));  
            fields.add(new NDCField("i",   getUntilNextFS(rawMessage, 106),                    false ));                                             // Var (106) | *See* Table Note 1 |
            fields.add(new NDCField("FS",  rawMessage.readBytes(1),                            true  ));
            fields.add(new NDCField("j",   rawMessage.readBytes(8),                            false ));                                             // *See* Table Note 1 |
            fields.add(new NDCField("FS",  rawMessage.readBytes(1),                            true  ));                                                  
            fields.add(new NDCField("k",   rawMessage.readBytes(getAmountEntryLength()),       false ));                                             // 8 or 12 | See Table Note 1, Table Note 4 |
            fields.add(new NDCField("FS",  rawMessage.readBytes(1),                            true  ));
            fields.add(new NDCField("l",   getUntilNextFS(rawMessage, 32),                     false ));                   
            fields.add(new NDCField("FS",  rawMessage.readBytes(1),                            true  ));
            fields.add(new NDCField("m",   getUntilNextFS(rawMessage, 32),                     false ));
            fields.add(new NDCField("FS",  rawMessage.readBytes(1),                            true  ));
            fields.add(new NDCField("n",   getUntilNextFS(rawMessage, 32),                     false ));
            fields.add(new NDCField("FS",  rawMessage.readBytes(1),                            isMandatoryGroupWith(new String[]{"o", "p"})));      // Table Note 2: A field separator and optional fields ‘o’ and ‘p’ comprise a group. When included in the message, all the fields of this group must be present. The field separator must also be present if any of the succeeding fields are present.     
            fields.add(new NDCField("o",   rawMessage.readBytes(1),                            false ));           
            fields.add(new NDCField("p",   getUntilNextFS(rawMessage, 78),                     false )); 
            fields.add(new NDCField("FS",  rawMessage.readBytes(1),	                           isMandatoryDependOnECP(15)));                         // See Table Note 5 & Table Note 9
            fields.add(new NDCField("q",   rawMessage.readBytes(1),	                           isMandatoryDependOnECP(15)));                         // See Table Note 5
            fields.add(new NDCField("r",   getUntilNextFS(rawMessage, 71),	                   isMandatoryDependOnECP(15)));                         // See Table Note 5, Table Note 6 & Table Note 7            
            fields.add(new NDCField("FS",  getFS(rawMessage),                                  isMandatorySetAtTRS("av"))); 	                     // See Table Note 8, Table Note 8: The optional fields from ‘av1’ onwards with the preceding field separator form field groups. The name of each field is given by two alphabetic characters with each sub‐field element identified by a numeric character. These fields are used for general expansion of the transaction request message. Each field is identified by an ID field. Each field is only included in the message if the corresponding flag is set in the Transaction Request state. If the Transaction Request state specifies that a field will be included, at least the data identifier will be present. If the flag for a field is not set, the entire field and its preceding field separator are not included.          	            	                                                                                                                   Table Note 
            fields.add(new NDCField("av1", rawMessage.readBytes(1),                            isMandatorySetAtTRS("av")));                          // See Table Note 8, Table Note 9: If any of the fields from ‘av1’ onwards are included in the message, the field separators preceding fields ‘o’–‘p’ and ‘q’–‘r’ will be present even if the associated data is not.
            fields.add(new NDCField("av2", getUntilNextFS(rawMessage, 16),	                   false ));                                             // See Table Note 8, Table Note 9
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("aw")));                          // See Table Note 8, Table Note 9
            fields.add(new NDCField("aw1", rawMessage.readBytes(1),	                           isMandatorySetAtTRS("aw")));                          // See Table Note 8, Table Note 9
            fields.add(new NDCField("aw2", getUntilNextFS(rawMessage, 16),	                   false ));                                             // See Table Note 8, Table Note 9
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("ax")));                          // See Table Note 8, Table Note 9
            fields.add(new NDCField("ax1", rawMessage.readBytes(1),	                           isMandatorySetAtTRS("ax")));                          // See Table Note 8, Table Note 9, Table Note 10
            fields.add(new NDCField("ax2", getUntilNextFS(rawMessage),	                       false ));                                             // See Table Note 8, Table Note 9, Table Note 10
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("ay")));                          // See Table Note 8, Table Note 9
            fields.add(new NDCField("ay1", rawMessage.readBytes(1),	                           isMandatorySetAtTRS("ay")));                          // See Table Note 8, Table Note 9, Table Note 10
            fields.add(new NDCField("ay2", getUntilNextFS(rawMessage),	                       false ));                                             // See Table Note 8, Table Note 9, Table Note 10
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("az")));                          // See Table Note 8, Table Note 9
            fields.add(new NDCField("az1", rawMessage.readBytes(1),	                           isMandatorySetAtTRS("az")));                          // See Table Note 8, Table Note 9, Table Note 10
            fields.add(new NDCField("az2", getUntilNextFS(rawMessage),        	               false ));                                             // See Table Note 8, Table Note 9, Table Note 10
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("ba")));                          // See Table Note 8, Table Note 9
            fields.add(new NDCField("ba1", rawMessage.readBytes(1),	                           isMandatorySetAtTRS("ba")));                          // See Table Note 8, Table Note 9, Table Note 10
            fields.add(new NDCField("ba2", getUntilNextFS(rawMessage),	                       false ));                                             // See Table Note 8, Table Note 9, Table Note 10
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("bb")));                          // See Table Note 8, Table Note 9
            fields.add(new NDCField("bb1", rawMessage.readBytes(1),	                           isMandatorySetAtTRS("bb")));                          // See Table Note 8, Table Note 9, Table Note 10
            fields.add(new NDCField("bb2", getUntilNextFS(rawMessage),	                       false ));                                             // See Table Note 8, Table Note 9, Table Note 10
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("bc")));                          // See Table Note 8, Table Note 9
            fields.add(new NDCField("bc1", rawMessage.readBytes(1),	                           isMandatorySetAtTRS("bc")));                          // See Table Note 8, Table Note 9, Table Note 10
            fields.add(new NDCField("bc2", getUntilNextFS(rawMessage),        	               false ));                                             // See Table Note 8, Table Note 9, Table Note 10
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("bd")));                          // See Table Note 8, Table Note 9
            fields.add(new NDCField("bd1", rawMessage.readBytes(1),	                           isMandatorySetAtTRS("bd")));                          // See Table Note 8, Table Note 9, Table Note 10
            fields.add(new NDCField("bd2", getUntilNextFS(rawMessage),	                       false ));                                             // See Table Note 8, Table Note 9, Table Note 10
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("ca")));                          // See Table Note 12
            fields.add(new NDCField("ca",  getUntilNextFS(rawMessage),	                       false ));                                             // See Table Note 12, Table Note 13, Table Note 17
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("cb")));                          // See Table Note 14, Table Note 15, Table Note 16, Table Note 21 
            fields.add(new NDCField("cb",  getUntilNextFS(rawMessage),	                       false ));                                             // See Table Note 14, Table Note 15, Table Note 16, Table Note 21
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("cc")));                           
            fields.add(new NDCField("cc",  getUntilNextFS(rawMessage),	                       false ));                                             
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("cd")));                          
            fields.add(new NDCField("cd",  getUntilNextFS(rawMessage),	                       false ));                                             
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("ce")));                          
            fields.add(new NDCField("ce",  getUntilNextFS(rawMessage),        	               false ));                                             // See Table Note 8
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("cf")));                          // See Table Note 8
            fields.add(new NDCField("cf",  getUntilNextFS(rawMessage),	                       false ));                                             // See Table Note 8
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("cg")));                          // See Table Note 8
            fields.add(new NDCField("cg",  getUntilNextFS(rawMessage),	                       false ));                                             // See Table Note 8
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("ci")));                          // See Table Note 8
            fields.add(new NDCField("ci",  getUntilNextFS(rawMessage),        	               false ));                                             // See Table Note 8
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMandatorySetAtTRS("w")));                           // See Table Note 8
            fields.add(new NDCField("w",   getUntilNextFS(rawMessage),	                       false ));                                             // See Table Note 8
            fields.add(new NDCField("FS",  getFS(rawMessage),	                               isMACRequired()));                                    // See Table Note 8            
            fields.add(new NDCField("x",   rawMessage.readBytes(8),	                           isMACRequired()));                                    // See Table Note 8
            
            // This line won't execute because the exception occurs above
            System.out.println("Sucessful parsing");
        } 
        catch (IndexOutOfBoundsException e) {
            // Handle the specific exception
            System.out.println("Error : Index out of bound");
        }
        catch (Exception e) {
            // Generic exception handler (will catch all other exceptions)
            System.out.println("Error : General Error");
        }
        finally {
            // This block always executes, whether an exception occurred or not
        }    	    	
    }   
};    


