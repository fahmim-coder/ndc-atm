package com.leunca.ndc.advance.hosttoterminal;

import java.util.List;
import com.leunca.ndc.*;

public sealed class NDCHostToTerminal permits AcknowledgeAndStopEJ, AcknowledgeEJUploadBlock, CustomisationDataCommands, EJOptionsAndTimers, EncryptionKeyChange, ExtendedEncryptionKeyChange, InteractiveTransactionResponse, TerminalCommands, TMAlertHostToTerminal, TransactionReply {
    private byte[] message;
    private List<NDCField>fields;
    
    public NDCHostToTerminal(byte[] raw){
        message = raw.clone();
    };
    
    public byte[] getRawMessage() { return message; };
    public List<NDCField>getFields(){ return fields; };
}

