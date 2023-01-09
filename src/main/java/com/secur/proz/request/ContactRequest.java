package com.secur.proz.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
public class ContactRequest {
  private String contactId;
  private String contactName;
  private String contactEmail;
  private String subject;
  private String message;
  private Date createDt;
}
