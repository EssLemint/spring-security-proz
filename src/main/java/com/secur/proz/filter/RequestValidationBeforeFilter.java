package com.secur.proz.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
public class RequestValidationBeforeFilter extends OncePerRequestFilter {

  public static final String AUTHENTICATION_SCHEME_BASIC = "Basic";
  private Charset credentialsCharset = StandardCharsets.UTF_8;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    log.info("***** VALIDATION FILTER START *****");

    HttpServletRequest req = request;
    HttpServletResponse res = response;
    String authorization = req.getHeader(AUTHORIZATION);

    log.info("req = {}", req);
    log.info("res = {}", res);
    log.info("authorization = {}", authorization);

    if (!Objects.isNull(authorization)){
      String header = authorization.trim();
      if (StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC)) {
        byte[] bytes = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoded;
        try {
          decoded = Base64.getDecoder().decode(bytes);
          String token = new String(decoded, credentialsCharset);
          int delim = token.indexOf(":");
          if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication");
          }
          String email = token.substring(0, delim);
          if (email.toLowerCase().contains("test")) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
          }

        } catch (IllegalArgumentException e) {
          throw new BadCredentialsException("Failed to decode basic authentication");
        }
      }
    }
    filterChain.doFilter(request, response);
  }
}
