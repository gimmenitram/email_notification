package com.martin.rest;

import com.martin.persistence.model.Reminder;
import com.martin.service.ReminderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
public class ReminderController {

  @Inject
  private ReminderService reminderService;

  @GetMapping("/reminders")
  public ResponseEntity getReminders() {

    return ok(reminderService.getAllReminders());
  }

  @GetMapping("/reminders/{reminderid}")
  public ResponseEntity getReminder(@PathVariable String reminderid) {

    Optional<Reminder> reminder = reminderService.getReminder(reminderid);

    if(reminder.isPresent()) {
      return ok(reminderService.getReminder(reminderid));
    }

    return notFound().build();
  }

  @PostMapping("/reminders")
  public ResponseEntity insertReminder(@RequestBody Reminder reminder) {
    try {
      reminderService.insertReminder(reminder);

//    return created().build();
      return ok().build();

    } catch (IllegalArgumentException ex){
      return badRequest().body(ex.getMessage());
    }
  }

  @PutMapping("/reminders/{reminderid}")
  public ResponseEntity updateReminder(@PathVariable String reminderid, @RequestBody Reminder reminder) {
    try {
      reminderService.updateReminder(reminderid, reminder);

      return ok().build();

    } catch (IllegalArgumentException ex){
      return badRequest().body(ex.getMessage());
    }
  }

  @DeleteMapping("/reminders/{reminderid}")
  public ResponseEntity deleteReminder (@PathVariable String reminderid) {
    try {
      reminderService.deleteReminder(reminderid);

      return noContent().build();

    } catch (IllegalArgumentException ex){
      return badRequest().body(ex.getMessage());
    }
  }

}
