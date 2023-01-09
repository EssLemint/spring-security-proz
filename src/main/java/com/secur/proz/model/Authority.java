package com.secur.proz.model;


import jakarta.persistence.*;
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
@Table(name = "authorities")
public class Authority {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;


  public Authority(String name, Customer customer) {
    this.name = name;
    this.customer = customer;
  }
}
