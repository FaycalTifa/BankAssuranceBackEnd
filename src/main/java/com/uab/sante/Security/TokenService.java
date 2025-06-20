package com.uab.sante.Security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public Jwt getTokenInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Utilisateur non authentifié !");
        }

        if (authentication.getPrincipal() instanceof Jwt) {
            return (Jwt) authentication.getPrincipal();
        }

        throw new RuntimeException("Le principal n'est pas un JWT");
    }

    public String getUsernameFromToken() {
        Jwt jwt = getTokenInfo();
        return jwt.getClaimAsString("preferred_username");
    }

    public String getEmailFromToken() {
        Jwt jwt = getTokenInfo();
        return jwt.getClaimAsString("email");
    }
}
