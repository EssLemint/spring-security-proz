package com.secur.proz.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Date;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cards {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "card_id")
  private int cardId;

  @Column(name = "customer_id")
  private int customerId;

  @Column(name = "card_number")
  private String cardNumber;

  @Column(name = "card_type")
  private String cardType;

  @Column(name = "total_limit")
  private int totalLimit;

  @Column(name = "amount_used")
  private int amountUsed;

  @Column(name = "available_amount")
  private int availableAmount;

  @Column(name = "create_dt")
  private Date createDt;

  public Cards(int customerId, String cardNumber, String cardType, int totalLimit, int amountUsed, int availableAmount, Date createDt) {
    this.customerId = customerId;
    this.cardNumber = cardNumber;
    this.cardType = cardType;
    this.totalLimit = totalLimit;
    this.amountUsed = amountUsed;
    this.availableAmount = availableAmount;
    this.createDt = createDt;
  }

}
