package com.secur.proz.filter;

import com.secur.proz.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class JWTTokenValidatorFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    //get Authorization header value
    String jwt = request.getHeader(SecurityConstants.JWT_HEADER);

    if (!Objects.isNull(jwt)) {
      try {
        //발생한 jwt 토큰 key와 같은 SecretKey 생성
        SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(jwt.replace("Bearer ", "").trim())//pass the jwt that we received
            .getBody(); // want to read body part of jwt token


        String username = String.valueOf(claims.get("username"));
        String authorities = (String) claims.get("authorities");

        Authentication auth = new UsernamePasswordAuthenticationToken(username
            , null
            , AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
        //create UsernamePasswordAuthenticationToken with username and pwd(null),
        //and authority

        SecurityContextHolder.getContext().setAuthentication(auth);

      } catch (Exception e) {
        throw new BadCredentialsException("Invalid Token Received");
      }
    }

    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    return request.getServletPath().equals("/user");
  }
}
