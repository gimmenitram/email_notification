package com.martin.persistence.dao;

import com.martin.persistence.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountsDao {

    List<Account> getAllAccounts();

    Optional<Account> getAccount(String account);

    void saveOrUpdateAccount(Account account);

    void deleteAccount(String userName);
}
