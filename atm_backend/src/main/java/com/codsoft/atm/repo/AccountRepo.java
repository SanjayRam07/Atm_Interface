package com.codsoft.atm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codsoft.atm.model.Account;
import com.codsoft.atm.model.Transaction;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
    Account findByAccNo(String accNo);
}
