package com.secur.proz.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCreateRequest {

  private String name;
  private String email;
  private String pwd;
  private String mobileNumber;
  private String role;
}
