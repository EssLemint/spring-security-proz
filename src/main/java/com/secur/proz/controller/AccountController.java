package com.secur.proz.controller;

import com.secur.proz.model.Accounts;
import com.secur.proz.respository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

  private final AccountsRepository accountsRepository;

  @GetMapping("/my/account")
  public String getAccountDetails() {
    return "getAccountController";
  }

  @GetMapping("/myAccount")
  public ResponseEntity<?> getAccountDetails(@RequestParam int id) {
    Accounts accounts = accountsRepository.findByCustomerId(id);
    if (accounts != null ) {
      return ResponseEntity.ok(accounts);
    }else {
      return ResponseEntity.status(400).build();
    }
  }
}
