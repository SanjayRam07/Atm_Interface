package com.codsoft.atm.service;

import org.hibernate.type.StringNVarcharType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.codsoft.atm.model.Transaction;
import com.codsoft.atm.repo.TransactionRepo;

@Service
public class TransactionService {
    
    @Autowired
    TransactionRepo transactionRepo;

    public boolean saveTransaction(Transaction transaction) {
        transactionRepo.save(transaction);
        return true;
    }

    public boolean deposit(String accNo, Integer amt) {
        Transaction newTransaction = new Transaction();

        createTransactId(newTransaction);
        newTransaction.setAccountTo(accNo);
        newTransaction.setTransactType("deposit");
        newTransaction.setTransactAmount(amt);

        saveTransaction(newTransaction);
        return true;
    }

    public boolean withdraw(String accNo, Integer amt) {
        Transaction newTransaction = new Transaction();

        createTransactId(newTransaction);
        newTransaction.setAccountFrom(accNo);
        newTransaction.setTransactType("withdraw");
        newTransaction.setTransactAmount(amt);

        saveTransaction(newTransaction);
        return true;
    }

    public boolean transfer(String accFrom, String accTo, Integer amt) {
        Transaction newTransaction = new Transaction();

        createTransactId(newTransaction);
        newTransaction.setAccountFrom(accFrom);
        newTransaction.setAccountTo(accTo);
        newTransaction.setTransactType("transfer");
        newTransaction.setTransactAmount(amt);

        saveTransaction(newTransaction);
        return true;
    }

    public List<Transaction> findAllTransaction() {
        return transactionRepo.findAll();
    }

    public Transaction findById(String id) {
        return transactionRepo.findByTransactId(id);
    }

    public List<Transaction> findTransactionsByUser(String accNo) {
        List<Transaction> list = new ArrayList<>();

        list.addAll(transactionRepo.findAllByAccountFrom(accNo));
        list.addAll(transactionRepo.findAllByAccountTo(accNo));
        
        return list;
    }

    private void createTransactId(Transaction transaction) {
        String newTransactId = getRandomString();

        if(transactionRepo.findByTransactId(newTransactId)!=null) 
            createTransactId(transaction);
        else
            transaction.setTransactId(newTransactId);
    }
    
    protected String getRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
