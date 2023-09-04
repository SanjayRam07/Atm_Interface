package com.codsoft.atm.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codsoft.atm.model.Account;
import com.codsoft.atm.repo.AccountRepo;

@Service
public class AccountService {
    
    @Autowired
    AccountRepo accountRepo;

    public Account findByAccNo(String accNo) {
        return accountRepo.findByAccNo(accNo);
    }

    public List<Account> findAllAcc()  {
        return accountRepo.findAll();
    }

    public void addAccount(Account account) {
        setAccountNo(account);
        accountRepo.save(account);
    }

    public boolean deleteAccount(Account account, String password) {
        if(this.validate(account.getAccNo(), password)) {
            accountRepo.delete(account);
            return true;
        }
        return false;  
    }

    public boolean updateAccountBalance(String accNo,  String password, int balance) {
        if(this.validate(accNo, password)) {
            Account oldAcc=accountRepo.findByAccNo(accNo);
            oldAcc.setBalance(balance);
            accountRepo.save(oldAcc);
            return true;
        }
        return false;
    }

    public boolean depositBalance(String accNo,  String password, int balance) {
        if(this.validate(accNo, password)) {
            Account oldAcc=accountRepo.findByAccNo(accNo);
            oldAcc.setBalance(oldAcc.getBalance() + balance);
            accountRepo.save(oldAcc);
            return true;
        }
        return false;
    }

    public boolean withdrawBalance(String accNo,  String password, int balance) {
        if(this.validate(accNo, password)) {
            Account oldAcc=accountRepo.findByAccNo(accNo);
            oldAcc.setBalance(oldAcc.getBalance() - balance);
            accountRepo.save(oldAcc);
            return true;
        }
        return false;
    }

    public boolean transferBalance(String accFrom, String accTo, String password, int amt) {
        if(this.validate(accFrom, password)) {
            Account accountFrom=accountRepo.findByAccNo(accFrom);
            accountFrom.setBalance(accountFrom.getBalance() - amt);
            Account accountTo=accountRepo.findByAccNo(accTo);
            accountTo.setBalance(accountTo.getBalance() + amt);
            return true;          
        }
        return false;
    }

    public boolean updateAccountPassword(String accNo, String oldPassword, String newPassword) {
        if(this.validate(accNo, oldPassword)) {
            Account oldAcc=accountRepo.findByAccNo(accNo);
            oldAcc.setPassword(newPassword);
            accountRepo.save(oldAcc);
            return true;
        }
        return false;
    }

    public void setAccountNo(Account account) {
        int max=10000, min=1000;
        Random random=new Random();
        Integer randNum=random.nextInt(max-min+1)+min;

        String type=account.getAccType();
        String accNum="";

        if(type.equals("savings")) 
            accNum="SAV"+randNum;
        else if(type.equals("current"))
            accNum="CUR"+randNum;

        if(accountRepo.findByAccNo(accNum)!=null) 
            setAccountNo(account);
        else 
            account.setAccNo(accNum);
    }

    public boolean validate(String accNo, String password) {
        Account account=accountRepo.findByAccNo(accNo);
        try {
            String accPwd=account.getPassword();
            if(accPwd==null || accPwd.equals(password)) 
            return true;
        }
        catch(Exception e) {
            return false;
        }
        return false;
    }

}
