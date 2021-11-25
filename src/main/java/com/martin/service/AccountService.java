package com.martin.service;

import com.martin.persistence.dao.DynamoDBAccountsDao;
import com.martin.persistence.model.Account;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class AccountService {

    private static final Logger LOG = Logger.getLogger(AccountService.class);

    private static final DynamoDBAccountsDao accountsDao = DynamoDBAccountsDao.instance();

    public List<Account> getAllAccounts() {

        LOG.info("getAllAccounts invoked to scan table for ALL accounts");
        List<Account> accounts = accountsDao.getAllAccounts();
        LOG.info("Found " + accounts.size() + " total accounts.");
        return accounts;
    }

    public Optional<Account> getAccount(String username) {

        LOG.info(String.format("getAccount invoked to scan table for %s", username));
        return accountsDao.getAccount(username);
    }

    private void validateAccount(Account account) {
        if (isNull(account) || isNull(account.getUsername())) {
            LOG.error("received null input");
            throw new IllegalArgumentException("Cannot save null object");
        }
    }

    public void insertAccount(Account account) {

        validateAccount(account);

        Optional<Account> optionalAccount = getAccount(account.getUsername());
        if (optionalAccount.isPresent()){
            String usernameExisting = "username existing";

            LOG.error(usernameExisting);
            throw new IllegalArgumentException(usernameExisting);
        }

        saveOrUpdateAccount(account);
    }

    public void updateAccount(String username, Account account) {

        validateAccount(account);

        if (!username.equals(account.getUsername())) {
            String usernameMismatched = "username mismatched";

            LOG.error(usernameMismatched);
            throw new IllegalArgumentException(usernameMismatched);
        }

        Optional<Account> optionalAccount = getAccount(account.getUsername());
        if (!optionalAccount.isPresent()){
            String usernameNotExisting = "cannot find username";

            LOG.error(usernameNotExisting);
            throw new IllegalArgumentException(usernameNotExisting);
        }

        saveOrUpdateAccount(account);
    }

    public void saveOrUpdateAccount(Account account) {
        accountsDao.saveOrUpdateAccount(account);
        LOG.info("Successfully saved/updated event");
    }


    public void deleteAccount(String username) {

        accountsDao.deleteAccount(username);
        LOG.info("Successfully deleted account");
    }
}
