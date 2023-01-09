package com.secur.proz.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Accounts {
  @Id
  @Column(name="account_number")
  private int accountNumber;

  @Column(name = "customer_id")
  private int customerId;

  @Column(name="account_type")
  private String accountType;

  @Column(name = "branch_address")
  private String branchAddress;

  @Column(name = "create_dt")
  private String createDt;

  public Accounts(int customerId, int accountNumber, String accountType, String branchAddress, String createDt) {
    this.customerId = customerId;
    this.accountNumber = accountNumber;
    this.accountType = accountType;
    this.branchAddress = branchAddress;
    this.createDt = createDt;
  }
}
