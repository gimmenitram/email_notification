package com.martin.persistence.model;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.Serializable;


@DynamoDBTable(tableName = "reminders")
public class Reminder implements Serializable {

    private static final long serialVersionUID = -8243145429438016232L;

    @DynamoDBHashKey
    private String reminderid;

    @DynamoDBAttribute
    private String reminderphrase;

    public Reminder() { }

    public Reminder(String reminderid, String reminderphrase) {
        this.reminderid = reminderid;
        this.reminderphrase = reminderphrase;
    }

    public String getReminderid() {
        return reminderid;
    }

    public void setReminderid(String reminderid) {
        this.reminderid = reminderid;
    }

    public String getReminderphrase() {
        return reminderphrase;
    }

    public void setReminderphrase(String reminderphrase) {
        this.reminderphrase = reminderphrase;
    }
}
