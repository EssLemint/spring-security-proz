package com.secur.proz.controller;

import com.secur.proz.model.Cards;
import com.secur.proz.respository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

  private final CardsRepository cardsRepository;

  @GetMapping("/my/cards")
  public String getCardsDetails() {
    return "getCardsDetails";
  }

  @GetMapping("/myCards")
  public ResponseEntity<?> getCardDetails(@RequestParam int id) {
    List<Cards> cards = cardsRepository.findByCustomerId(id);
    if (cards != null ) {
      return ResponseEntity.ok(cards);
    }else {
      return ResponseEntity.status(400).build();
    }
  }
}
