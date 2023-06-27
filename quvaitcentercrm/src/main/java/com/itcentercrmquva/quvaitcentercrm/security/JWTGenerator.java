package com.itcentercrmquva.quvaitcentercrm.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JWTGenerator {

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
        return Jwts.builder().setSubject(username).setIssuedAt(currentDate).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_BOX).compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(SecurityConstants.JWT_BOX).parseClaimsJws(token.trim()).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SecurityConstants.JWT_BOX).parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("Token is expired or incorrect");
        }
    }
}
