package com.secur.proz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Set;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id")
  private Long Id;
  private String name;
  private String email;
  @Column(name = "mobile_number")
  private String mobileNumber;
  private String pwd;
  private String role;
  @Column(name = "create_dt")
  private String createDt;

  @JsonIgnore
  @OneToMany(mappedBy="customer",fetch=FetchType.EAGER)
  private Set<Authority> authorities;

  public Customer(String name, String email, String mobileNumber, String pwd, String role, String createDt, Set<Authority> authorities) {
    this.name = name;
    this.email = email;
    this.mobileNumber = mobileNumber;
    this.pwd = pwd;
    this.role = role;
    this.createDt = createDt;
    this.authorities = authorities;
  }

  public void updateCutomer(String name, String email, String mobileNumber, String pwd, String role) {
    this.name = name;
    this.email = email;
    this.mobileNumber = mobileNumber;
    this.pwd = pwd;
    this.role = role;
  }
}
