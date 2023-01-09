package com.secur.proz.filter;

import com.secur.proz.constants.SecurityConstants;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    //사용자 정보 추출
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (!Objects.isNull(authentication)) {  //사용자가 존재할 시
      //Key 생성
      SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

      // JWT 생성
      String jwt = Jwts.builder()
          .setIssuer("Proz")  // Jwt 발행을 누가하는지
          .setSubject("Jwt Token")  // can set any value in subject
          .claim("username", authentication.getName()) //username
          .claim("authorities", populateAuthorities(authentication.getAuthorities())) //authorities
          .setIssuedAt(new Date())  //발행 날짜
          .setExpiration(new Date((new Date()).getTime() + 3000000))  //만료 날짜
          .signWith(key)  //send secret key
          .compact();

      response.setHeader(SecurityConstants.JWT_HEADER, jwt);
      //setting in header : name - authorization, value - jwt
    }

    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    //it will not execute filter
    return !request.getServletPath().equals("/user");
  }

  private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
    Set<String> authoritiesSet = new HashSet<>();
    for (GrantedAuthority authority : collection) {
      authoritiesSet.add(authority.getAuthority());
    }
    return String.join(",", authoritiesSet);
  }
}
