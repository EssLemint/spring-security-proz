package com.secur.proz.auth;

import com.secur.proz.model.Authority;
import com.secur.proz.model.Customer;
import com.secur.proz.respository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ProzAuthProvider implements AuthenticationProvider {

  private final CustomerRepository repository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String pwd = authentication.getCredentials().toString();
    List<Customer> customerList = repository.findByEmail(username);

    if (customerList.isEmpty()) {
      throw new UsernameNotFoundException("User Not Found");
    } else {
      if (passwordEncoder.matches(pwd, customerList.get(0).getPwd())) {
        return new UsernamePasswordAuthenticationToken(
            username, pwd, getGrantedAuthorities(customerList.get(0).getAuthorities())
        );
      } else {
        throw  new BadCredentialsException("Password failed");
      }
    }
  }

  private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authoritySet) {
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    for (Authority authority : authoritySet) {
      grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
    }

    return grantedAuthorities;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
