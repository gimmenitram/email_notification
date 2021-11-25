package com.martin.persistence.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.martin.persistence.model.Account;
import com.martin.manager.DynamoDBManager;
import org.apache.log4j.Logger;

import java.util.*;


public class DynamoDBAccountsDao implements AccountsDao {

    private static final Logger log = Logger.getLogger(DynamoDBAccountsDao.class);

    private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

    private static volatile DynamoDBAccountsDao instance;


    private DynamoDBAccountsDao() {
    }

    public static DynamoDBAccountsDao instance() {

        if (instance == null) {
            synchronized (DynamoDBAccountsDao.class) {
                if (instance == null)
                    instance = new DynamoDBAccountsDao();
            }
        }
        return instance;
    }

    @Override
    public List<Account> getAllAccounts() {
        return mapper.scan(Account.class, new DynamoDBScanExpression());
    }

    @Override
    public Optional<Account> getAccount(String userName) {

        Account account = mapper.load(Account.class, userName);

        return Optional.ofNullable(account);
    }

    @Override
    public void saveOrUpdateAccount(Account event) {
        mapper.save(event);
    }

    @Override
    public void deleteAccount(String userName) {

        Optional<Account> optionalAccount = getAccount(userName);
        if (optionalAccount.isPresent()) {
            mapper.delete(optionalAccount.get());
        }        else {
            throw new IllegalArgumentException("Delete failed for nonexistent account");
        }
    }

}
