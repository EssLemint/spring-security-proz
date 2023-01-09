package com.secur.proz.login;

import com.secur.proz.model.Customer;
import com.secur.proz.request.CustomerCreateRequest;
import com.secur.proz.request.SingInRequest;
import com.secur.proz.respository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginController {

  private final LoginService service;
  private final CustomerRepository repository;
  
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody CustomerCreateRequest request) {
    Long savesUserId = service.registerUser(request);
    return ResponseEntity.ok(savesUserId);
  }

  @RequestMapping("/user")
  public ResponseEntity<?> getUserDetailsAfterLogin(Authentication authentication) {
    List<Customer> customers = repository.findByEmail(authentication.getName());
    if (!customers.isEmpty()) {
      return ResponseEntity.ok(customers.get(0));
    } else {
      return null;
    }
  }
}
