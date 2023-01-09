package com.secur.proz.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class AccountTransactions {

  @Id
  @Column(name = "transaction_id")
  private String transactionId;

  @Column(name="account_number")
  private long accountNumber;

  @Column(name = "customer_id")
  private int customerId;

  @Column(name="transaction_dt")
  private Date transactionDt;

  @Column(name = "transaction_summary")
  private String transactionSummary;

  @Column(name="transaction_type")
  private String transactionType;

  @Column(name = "transaction_amt")
  private int transactionAmt;

  @Column(name = "closing_balance")
  private int closingBalance;

  @Column(name = "create_dt")
  private String createDt;


  public AccountTransactions(String transactionId, long accountNumber, int customerId, Date transactionDt, String transactionSummary, String transactionType, int transactionAmt, int closingBalance, String createDt) {
    this.transactionId = transactionId;
    this.accountNumber = accountNumber;
    this.customerId = customerId;
    this.transactionDt = transactionDt;
    this.transactionSummary = transactionSummary;
    this.transactionType = transactionType;
    this.transactionAmt = transactionAmt;
    this.closingBalance = closingBalance;
    this.createDt = createDt;
  }
}
