package com.martin.persistence.dao;

import com.martin.persistence.model.Reminder;

import java.util.List;
import java.util.Optional;

public interface RemindersDao {

    List<Reminder> getAllReminders();

    Optional<Reminder> getReminder(String reminderid);

    void saveOrUpdateReminder(Reminder reminderid);

    void deleteReminder(String reminderId);
}
