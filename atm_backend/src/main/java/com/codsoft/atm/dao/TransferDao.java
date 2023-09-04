package com.codsoft.atm.dao;

public class TransferDao {
    
    private String accFrom;
    private String accTo;
    private String password;
    private Integer amt;

    public String getAccFrom() {
        return accFrom;
    }
    public void setAccFrom(String accFrom) {
        this.accFrom = accFrom;
    }
    public String getAccTo() {
        return accTo;
    }
    public void setAccTo(String accTo) {
        this.accTo = accTo;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getAmt() {
        return amt;
    }
    public void setAmt(Integer amt) {
        this.amt = amt;
    }

    
}
