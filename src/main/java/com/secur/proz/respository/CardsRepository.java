package com.secur.proz.respository;

import com.secur.proz.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {
  List<Cards> findByCustomerId(int customerId);
}
