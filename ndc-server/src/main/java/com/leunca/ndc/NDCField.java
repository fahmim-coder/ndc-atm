package com.leunca.ndc;

import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;

public class NDCField {
    private ByteBuf field;
    private String identifier;
    private boolean isMandatory;
    
    public ByteBuf getRawField(){ return field; };
    public String getIdentifier(){ return identifier; };
    public boolean isMandatory(){ return isMandatory; };
    
    public NDCField(String identifier, ByteBuf field, boolean isMandatory) {
        if (field == null && isMandatory) {
            throw new IllegalArgumentException("Mandatory field cannot be null");
        }
        this.field = field != null ? field.retain() : null; // Retain on construction
        this.identifier = identifier;
        this.isMandatory = isMandatory;
    }

    @SuppressWarnings("removal")
	@Override
    protected void finalize() throws Throwable {
        try {
            if (field != null) {
                ReferenceCountUtil.release(field); // Safely release
            }
        } finally {
            super.finalize();
        }
    }
};