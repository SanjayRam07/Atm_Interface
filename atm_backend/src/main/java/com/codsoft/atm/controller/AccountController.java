package com.codsoft.atm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codsoft.atm.dao.AccountDao;
import com.codsoft.atm.model.Account;
import com.codsoft.atm.model.Transaction;
import com.codsoft.atm.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
    
    @Autowired
    AccountService accountService;

    @PostMapping("/create")
    public String createAccount(@RequestBody Account account) {
        accountService.addAccount(account);
        return "success";
    }

    @GetMapping("/showAll")
    public List<AccountDao> showAllAccounts() {
        List<Account> accList=accountService.findAllAcc();
        List<AccountDao> accDtoList=new ArrayList<>();
        
        for(Account acc:accList) {
            accDtoList.add(new AccountDao(acc));
        }

        return accDtoList;
    }

    @GetMapping("/getAccount")
    public AccountDao findAccount(@RequestParam String accNo) {
        Account account=accountService.findByAccNo(accNo);
        return new AccountDao(account);
    }

    @DeleteMapping("/delete")
    public String deleteAccount(@RequestParam String accNo, @RequestParam String password) {
        Account account=accountService.findByAccNo(accNo);
        boolean status=accountService.deleteAccount(account, password);
        if(status) return "Account deleted";
        else return "Account not deleted";
    }

    @PostMapping("/updateBalance")
    public String updateAccount(@RequestParam String accNo, @RequestParam int balance, @RequestParam String password) {
        boolean status=accountService.updateAccountBalance(accNo, password, balance);
        if(status) return "Account updated";
        else return "Account update failed";
    }

    @PostMapping("/updatePassword")
    public String updateAccount(@RequestParam String accNo, @RequestParam String oldPassword, @RequestParam String newPassword) {
        boolean status=accountService.updateAccountPassword(accNo, oldPassword, newPassword);
        if(status) return "Account updated";
        else return "Account update failed";
    }

    @PostMapping("/signin")
    public boolean signin(@RequestParam String accNo, @RequestParam String password) {
        return accountService.validate(accNo, password);
    }
}
