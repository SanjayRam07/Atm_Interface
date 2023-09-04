package com.codsoft.atm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codsoft.atm.dao.DepositDao;
import com.codsoft.atm.dao.TransferDao;
import com.codsoft.atm.dao.WithdrawDao;
import com.codsoft.atm.model.Transaction;
import com.codsoft.atm.service.AccountService;
import com.codsoft.atm.service.TransactionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;
    
    @PostMapping(value="/deposit")
    public String deposit(@RequestBody DepositDao accDetails) {
        if(accountService.depositBalance(accDetails.getAccNo(), accDetails.getPassword(), accDetails.getAmt())) {
            transactionService.deposit(accDetails.getAccNo(), accDetails.getAmt());
            return "deposit success";
        }
        return "deposit failed";
    }

    @PostMapping(value="/withdraw")
    public String withdraw(@RequestBody WithdrawDao withdrawDao) {
        if(accountService.withdrawBalance(withdrawDao.getAccNo(), withdrawDao.getPassword(), withdrawDao.getAmt())) {
            transactionService.withdraw(withdrawDao.getAccNo(), withdrawDao.getAmt());
            return "withdraw success";
        }
        return "withdraw failed";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferDao transferDao) {
        if(accountService.transferBalance(transferDao.getAccFrom(), transferDao.getAccTo(), transferDao.getPassword(), transferDao.getAmt())) {
            transactionService.transfer(transferDao.getAccFrom(), transferDao.getAccTo(), transferDao.getAmt());
            return "transfer success";
        }
        return "transfer failed";
    }

    @GetMapping("/getAllTransaction") 
    public List<Transaction> getAllTransaction() {
        return transactionService.findAllTransaction();
    }

    @GetMapping("/getTransactionsByUser")
    public List<Transaction> getTransactionsByUser(@RequestParam String userId) {
        return transactionService.findTransactionsByUser(userId);
    }

    @GetMapping("/getById")
    public Transaction getById(String id) {
        return transactionService.findById(id);
    }
}