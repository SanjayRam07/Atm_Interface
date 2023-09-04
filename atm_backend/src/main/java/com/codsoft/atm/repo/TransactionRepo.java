package com.codsoft.atm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codsoft.atm.model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
    Transaction findByTransactId(String id);
    List<Transaction> findAllByAccountFrom(String accNo);
    List<Transaction> findAllByAccountTo(String accNo);
}
