package com.martin.service;

import com.martin.persistence.dao.DynamoDBRemindersDao;
import com.martin.persistence.model.Reminder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class ReminderService {

    private static final Logger LOG = Logger.getLogger(ReminderService.class);

    private static final DynamoDBRemindersDao remindersDao = DynamoDBRemindersDao.instance();

    public List<Reminder> getAllReminders() {

        LOG.info("getAllReminders invoked to scan table for ALL reminders");
        List<Reminder> reminders = remindersDao.getAllReminders();
        LOG.info("Found " + reminders.size() + " total reminders.");
        return reminders;
    }

    public Optional<Reminder> getReminder (String reminderid) {

        LOG.info(String.format("getReminder invoked to scan table for %s", reminderid));
        return remindersDao.getReminder(reminderid);
    }

    private void validateReminder(Reminder reminder) {
        if (isNull(reminder) || isNull(reminder.getReminderid())) {
            LOG.error("received null input");
            throw new IllegalArgumentException("Cannot save null object");
        }
    }

    public void insertReminder(Reminder reminder) {

        validateReminder(reminder);

        Optional<Reminder> optionalReminder = getReminder(reminder.getReminderid());
        if (optionalReminder.isPresent()){
            String reminderidExisting = "reminderid existing";

            LOG.error(reminderidExisting);
            throw new IllegalArgumentException(reminderidExisting);
        }

        saveOrUpdateReminder(reminder);
    }

    public void updateReminder(String reminderid, Reminder reminder) {

        validateReminder(reminder);

        if (!reminderid.equals(reminder.getReminderid())) {
            String reminderidMismatched = "reminder mismatched";

            LOG.error(reminderidMismatched);
            throw new IllegalArgumentException(reminderidMismatched);
        }

        Optional<Reminder> optionalReminder = getReminder(reminder.getReminderid());
        if (!optionalReminder.isPresent()){
            String reminderidNotExisting = "cannot find reminderid";

            LOG.error(reminderidNotExisting);
            throw new IllegalArgumentException(reminderidNotExisting);
        }

        saveOrUpdateReminder(reminder);
    }

    public void saveOrUpdateReminder(Reminder reminder) {
        remindersDao.saveOrUpdateReminder(reminder);
        LOG.info("Successfully saved/updated event");
    }


    public void deleteReminder(String reminderid) {

        remindersDao.deleteReminder(reminderid);
        LOG.info("Successfully deleted reminder");
    }
}
