package com.leunca.ndc.advance;

import java.util.List;

public class BunchChequeDepositCurrencyType {
    protected String depositCurrency;                            // Deposit Currency. This field contains the three‐character ISO 4217 currency code for the deposited cheques. If multiple currencies are deposited, fields cg4 to cg16 are repeated for each deposited currency
    protected String amountExponentSign;                         // Amount Exponent Sign. This field contains the amount exponent sign, which is either ‘+’ or ‘‐’   
    protected String amountExponentValue;                        // Amount Exponent Value. This field contains the exponent value for all cheque amounts reported in the bunch cheque deposit buffer. The actual cheque amount relates to the amount reported as follows: <cheque_amount> =  <cheque_amount_reported> * 10^<Exponent> This field together with the Amount Exponent Sign field defines the <Exponent> value For example, if the exponent is ‐2 and the cheque amount reported is 1234, the cheque amount is 12.34   
    protected String totalCustomerAmount;                  
    protected String totalDerivedAmount;
    private List<ChequeGroupType> chequeGroup;
}
