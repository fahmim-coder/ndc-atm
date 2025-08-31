package com.leunca.ndc.advance;

public class LastTransactionStatusDataType {
	
	public LastTransactionStatusDataType(){};

    private final String Identifier = "2";
    
    public String getIdentifier() { return Identifier; };
    
    protected String lastTransactionSerialNumber;                                             // Last Transaction Serial Number ‐ 4 bytes
    protected LastStatusIssued lastStatusIssued;                                      // Last Status Issued ‐ 1 byte. ‘0’ ‐ none sent, ‘1’ ‐ good termination sent, ‘2’ ‐ error status sent, ‘3’ ‐ transaction reply rejected.  
    
    //  If option 76 is set to 000 (four cassette types), Last Transaction Notes
    //  Dispensed Data contains four five‐digit decimal counts defining the
    //  notes dispensed on the last transaction if the last function command
    //  received and processed was a dispense command. These counts will be
    //  zero if the last function command received and processed was not a
    //  dispense command.
    //  If option 76 is set to 001 (seven cassette types), Last Transaction Notes
    //  Dispensed Data contains seven five‐digit decimal counts defining the
    //  notes dispensed on the last transaction if the last function command
    //  received and processed was a dispense command. These counts will be
    //  zero if the last function command received and processed was not a
    //  dispense command.
    //  
    //  Last Transaction Notes Dispensed Data ‐ 20 bytes or Var bytes.
    //  The Last Transaction Notes Dispensed data consists
    //  of 4 or 7 five‐digit decimal counts, defining the notes dispensed in
    //  the last dispense transaction. If the last received command was not a
    //  dispense command, these counts will be zero.
    
    protected int lastTransactionNotesDispensedFromCassette1;               // 5 bytes      
    protected int lastTransactionNotesDispensedFromCassette2;               // 5 bytes    
    protected int lastTransactionNotesDispensedFromCassette3;               // 5 bytes
    protected int lastTransactionNotesDispensedFromCassette4;               // 5 bytes    
    protected int lastTransactionNotesDispensedFromCassette5;               // 5 bytes
    protected int lastTransactionNotesDispensedFromCassette6;               // 5 bytes   
    protected int lastTransactionNotesDispensedFromCassette7;               // 5 bytes
    
    protected int lastTransactionCoinageAmountDispensed;                    // Last Transaction Coinage Amount Dispensed ‐ 5 bytes (always 00000) 
    
    //  If a Coin Dispenser is present and option 79 is set to 000 (four hopper
    //  types), the Last Transaction Coins Dispensed Data contains four
    //  five‐digit decimal counts defining the coins dispensed from each hopper
    //  on the last dispense transaction. These coin counts (20 bytes) will be zero
    //  if the last function command was not a coin dispense type function, or if
    //  this is the first transaction after installation.
    //  If a Coin Dispenser is present and option 79 is set to 001 (more than four
    //  hopper types), the 25 bytes of coin data in field ‘r’ are all set to zeros, and
    //  the Last Transaction Coins Dispensed Data is provided in fields ‘cf1’ to
    //  ‘cf <n+1>’ (data ID ʹfʹ) rather than field ʹrʹ. If a Coin Dispenser is not
    //  present, all 25 bytes are zero.
    
    protected int lastTransactionCoinsDispensedFromCassette1;               // 5 bytes             
    protected int lastTransactionCoinsDispensedFromCassette2;               // 5 bytes
    protected int lastTransactionCoinsDispensedFromCassette3;               // 5 bytes
    protected int lastTransactionCoinsDispensedFromCassette4;               // 5 bytes

    // The Last Cash Deposit Transaction Direction is present if a BNA or
    // GBXX is present and contains 1 byte as follows:
    // ‘0’ ‐ last transaction was not a cash deposit
    // ‘1’ ‐ vault direction
    // ‘2’ ‐ refund direction.
    
    protected LastCashDepositTransactionDirection lastCashDepositTransactionDirection;

    //  Where bit 0 of option 45 (BNA Settings) is set, the following data will also
    //  be included:

    protected int numberOfNotesRefunded;                            // Number of Notes Refunded during last transaction ‐ 5 bytes
    protected int numberOfNotesRejected;                            // Number of Notes Rejected during last transaction ‐ 5 bytes
    protected int numberOfNotesEncashed;                            // Number of Notes Encashed during last transaction ‐ 5 bytes
    protected int numberOfNotesToEscrow;                            // Number of Notes Escrowed during last transaction ‐ 5 bytes
    
    
    //  Where bits 1, 3 and 6 of option 45 (BNA Settings) are set, the following
    //  data will also be included:
    //  Number of recycle cassettes reported ‐ 2 bytes
    //  NDC Cassette Type ‐ 3 bytes
    //  Number of Notes ‐ 3 bytes
    //  Number of Recycle Cassettes Reported. Consists of a two‐digit decimal
    //  count in the range 00 to 99
    //  If no recycle cassette has accepted notes, this field contains 00 and the
    //  NDC Cassette Type and Number of Notes fields are not be included. If
    //  this is non‐zero, it indicates how many times the NDC Cassette Type and
    //  Number of Notes pair are repeated.
    //  NDC Cassette Type. Consists of one three‐digit decimal identifier. The
    //  identifier can be between 001 and 007 depending on the cash handler
    //  configuration. This field is repeated the same number of times as the
    //  number of recycle cassettes reported.
    //  If no recycle cassette has accepted notes, this field is omitted.
    //  Number of Notes. Consists of a three‐digit decimal count of the number
    //  of notes moved to the recycle cassette, in the range 001 to 999. This field
    //  is repeated the same number of times as the number of recycle cassettes
    //  reported.
    // If no recycle cassette has accepted notes, this field is omitted.
    
    
    protected RecycleCassettesGroup recycleCassettesGroup;
}
