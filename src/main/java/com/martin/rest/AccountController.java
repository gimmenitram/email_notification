package com.martin.rest;

import com.martin.persistence.model.Account;
import com.martin.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
public class AccountController {

  @Inject
  private AccountService accountService;

  @GetMapping("/accounts")
  public ResponseEntity getAccounts() {

    return ok(accountService.getAllAccounts());
  }

  @GetMapping("/accounts/{username}")
  public ResponseEntity getAccount(@PathVariable String username) {

    Optional<Account> account = accountService.getAccount(username);

    if(account.isPresent()) {
      return ok(accountService.getAccount(username));
    }

    return notFound().build();
  }

  @PostMapping("/accounts")
  public ResponseEntity insertAccount(@RequestBody Account account) {
    try {
      accountService.insertAccount(account);

//    return created().build();
      return ok().build();

    } catch (IllegalArgumentException ex){
      return badRequest().body(ex.getMessage());
    }
  }

  @PutMapping("/accounts/{username}")
  public ResponseEntity updateAccount(@PathVariable String username, @RequestBody Account account) {
    try {
      accountService.updateAccount(username, account);

      return ok().build();

    } catch (IllegalArgumentException ex){
      return badRequest().body(ex.getMessage());
    }
  }

  @DeleteMapping("/accounts/{username}")
  public ResponseEntity deleteAccount(@PathVariable String username) {
    try {
      accountService.deleteAccount(username);

      return noContent().build();

    } catch (IllegalArgumentException ex){
      return badRequest().body(ex.getMessage());
    }
  }

}
