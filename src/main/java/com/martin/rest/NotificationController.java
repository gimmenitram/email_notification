package com.martin.rest;

import com.martin.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.springframework.http.ResponseEntity.noContent;

@RestController
public class NotificationController {

  @Inject
  private NotificationService notificationService;

  @PostMapping(value = "/email-notif")
  public ResponseEntity notifyUser() {

    notificationService.notif();

    return noContent().build();
  }

}
