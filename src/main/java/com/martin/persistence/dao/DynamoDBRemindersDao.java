package com.martin.persistence.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.martin.manager.DynamoDBManager;
import com.martin.persistence.model.Reminder;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;


public class DynamoDBRemindersDao implements RemindersDao {

    private static final Logger log = Logger.getLogger(DynamoDBRemindersDao.class);

    private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

    private static volatile DynamoDBRemindersDao instance;


    private DynamoDBRemindersDao() {
    }

    public static DynamoDBRemindersDao instance() {

        if (instance == null) {
            synchronized (DynamoDBRemindersDao.class) {
                if (instance == null)
                    instance = new DynamoDBRemindersDao();
            }
        }
        return instance;
    }

    @Override
    public List<Reminder> getAllReminders() {
        return mapper.scan(Reminder.class, new DynamoDBScanExpression());
    }

    @Override
    public Optional<Reminder> getReminder(String reminderId) {

        Reminder reminder = mapper.load(Reminder.class, reminderId);

        return Optional.ofNullable(reminder);
    }

    @Override
    public void saveOrUpdateReminder(Reminder event) {
        mapper.save(event);
    }

    @Override
    public void deleteReminder(String reminderId) {

        Optional<Reminder> optionalReminder = getReminder(reminderId);
        if (optionalReminder.isPresent()) {
            mapper.delete(optionalReminder.get());
        }        else {
            throw new IllegalArgumentException("Delete failed for nonexistent reminderid");
        }
    }

}
