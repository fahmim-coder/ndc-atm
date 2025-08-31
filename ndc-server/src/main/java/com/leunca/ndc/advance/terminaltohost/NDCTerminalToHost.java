package com.leunca.ndc.advance.terminaltohost;

import java.util.List;
import com.leunca.ndc.*;

import io.netty.buffer.ByteBuf;

public sealed class NDCTerminalToHost permits UnsolicitedStatus, TransactionRequest, UploadEJData, TMAlertTerminalToHost, SolicitedStatus, EncryptorInitialisationData {
    protected ByteBuf message;
    protected List<NDCField>fields;
    
    public NDCTerminalToHost(ByteBuf raw){
        message = raw;
    };        
}

