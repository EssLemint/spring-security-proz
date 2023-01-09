package com.secur.proz.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Date;

@Entity
@Table(name="loans")
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Loans {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "loan_number")
  private int loanNumber;

  @Column(name = "customer_id")
  private int customerId;

  @Column(name="start_dt")
  private Date startDt;

  @Column(name = "loan_type")
  private String loanType;

  @Column(name = "total_loan")
  private int totalLoan;

  @Column(name = "amount_paid")
  private int amountPaid;

  @Column(name = "outstanding_amount")
  private int outstandingAmount;

  @Column(name = "create_dt")
  private String createDt;


  public Loans(int customerId, Date startDt, String loanType, int totalLoan, int amountPaid, int outstandingAmount, String createDt) {
    this.customerId = customerId;
    this.startDt = startDt;
    this.loanType = loanType;
    this.totalLoan = totalLoan;
    this.amountPaid = amountPaid;
    this.outstandingAmount = outstandingAmount;
    this.createDt = createDt;
  }
}
