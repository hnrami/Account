
package com.meybise.Accounts;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LedgerData {
    private final IntegerProperty LedgerId;
    private final StringProperty LedgerName;
    private final StringProperty LedgerAddress;
    private final StringProperty LedgerGstno;
    private final StringProperty LedgerPhoneno;
    private final StringProperty LedgerDate;
    
    public LedgerData(Integer LedgerId,String LedgerName,String LedgerAddress,String LedgerGstno,String LedgerPhoneno,String LedgerDate) {
        this.LedgerId = new SimpleIntegerProperty(LedgerId);
        this.LedgerName = new SimpleStringProperty(LedgerName);
        this.LedgerAddress = new SimpleStringProperty(LedgerAddress);
        this.LedgerGstno = new SimpleStringProperty(LedgerGstno);
        this.LedgerPhoneno = new SimpleStringProperty(LedgerPhoneno);
        this.LedgerDate = new SimpleStringProperty(LedgerDate);
    }
	 public Integer getLedgerId(){
		    return LedgerId.get();
		    }
		    public String getLedgerName(){
		    return LedgerName.get();
		    }
		    public String getLedgerAddress(){
		    return LedgerAddress.get();
		    }
		    public String getLedgerGstno(){
		    return LedgerGstno.get();
		    }
		    public String getLedgerPhoneno(){
		    return LedgerPhoneno.get();
		    }
		     public String getLedgerDate(){
		    return LedgerDate.get();
		    }
    
    
    
    
}
