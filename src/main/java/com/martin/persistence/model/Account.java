package com.martin.persistence.model;


import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.io.Serializable;


@DynamoDBTable(tableName = "accounts")
public class Account implements Serializable {

    private static final long serialVersionUID = -8243145429438016232L;

    @DynamoDBHashKey
    private String username;

    @DynamoDBAttribute
    private String emailaddress;

    public Account() { }

    public Account(String username, String emailaddress) {
        this.username = username;
        this.emailaddress = emailaddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }
}
