package com.secur.proz.controller;

import com.secur.proz.model.AccountTransactions;
import com.secur.proz.respository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

  private AccountTransactionsRepository accountTransactionsRepository;

  @GetMapping("/my/balance")
  public String getBalanceController() {
    return "getBalanceController";
  }

  @GetMapping("/myBalance")
  public ResponseEntity<?> getBalanceDetails(@RequestParam int id) {
    List<AccountTransactions> accountTransactions = accountTransactionsRepository.
        findByCustomerIdOrderByTransactionDtDesc(id);
    if (accountTransactions != null ) {
      return ResponseEntity.ok(accountTransactions);
    }else {
      return ResponseEntity.status(400).build();
    }
  }
}
