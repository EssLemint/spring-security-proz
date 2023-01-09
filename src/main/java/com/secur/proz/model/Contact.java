package com.secur.proz.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Date;

@Entity
@Table(name = "contact_messages")
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contact {

  @Id
  @Column(name = "contact_id")
  private String contactId;

  @Column(name = "contact_name")
  private String contactName;

  @Column(name = "contact_email")
  private String contactEmail;

  private String subject;

  private String message;

  @Column(name = "create_dt")
  private Date createDt;


  public Contact(String contactId, String contactName, String contactEmail, String subject, String message, Date createDt) {
    this.contactId = contactId;
    this.contactName = contactName;
    this.contactEmail = contactEmail;
    this.subject = subject;
    this.message = message;
    this.createDt = createDt;
  }
}
