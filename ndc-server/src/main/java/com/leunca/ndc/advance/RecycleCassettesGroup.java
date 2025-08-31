package com.leunca.ndc.advance;

import java.util.ArrayList;
import java.util.List;

public class RecycleCassettesGroup {
    protected int numberOfRecycleCassettesReported;
    protected List<RecycleCassette> recycleCassette;
    
    public int getNumberOfRecycleCassettesReported() {
        return numberOfRecycleCassettesReported;
    }

    public List<RecycleCassette> getRecycleCassette() {
        if (recycleCassette == null) {
            recycleCassette = new ArrayList<RecycleCassette>();
        }
        return this.recycleCassette;
    }

}
