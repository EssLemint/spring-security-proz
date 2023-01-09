package com.secur.proz.controller;

import com.secur.proz.model.Loans;
import com.secur.proz.respository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

  private final LoanRepository loanRepository;

  @GetMapping("/my/loans")
  public String getLoansDetails() {
    return "getLoansDetails";
  }

  @GetMapping("/myLoans")
  public ResponseEntity<?> getLoanDetails(@RequestParam int id) {
    List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
    if (loans != null ) {
      return ResponseEntity.ok(loans);
    }else {
      return ResponseEntity.status(400).build();
    }
  }

}
