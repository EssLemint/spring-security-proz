package com.secur.proz.login;

import com.secur.proz.model.Authority;
import com.secur.proz.model.Customer;
import com.secur.proz.request.CustomerCreateRequest;
import com.secur.proz.request.SingInRequest;
import com.secur.proz.respository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LoginService {

  private final CustomerRepository repository;
  private final PasswordEncoder passwordEncoder;

  public Long registerUser(CustomerCreateRequest dto) {
    Customer customer = new Customer(dto.getName(), dto.getEmail(), dto.getMobileNumber()
        , passwordEncoder.encode(dto.getPwd()), dto.getRole()
        , String.valueOf(new Date(System.currentTimeMillis())),null);
    Customer savedCustomer = repository.save(customer);
    if (savedCustomer.getId() > 0) {
      return savedCustomer.getId();
    } else throw new RuntimeException(" error during register");
  }
}
