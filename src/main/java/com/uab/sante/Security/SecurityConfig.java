package com.uab.sante.Security;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()  // Configuration CORS
                .csrf().disable()  // Désactivation CSRF si nécessaire
                .authorizeRequests(authz -> authz
                        .antMatchers( "/api/civilite",
                                "/api/banque",
                                "/api/agence",
                                "/api/civilite",
                                "/api/detailsCredit",
                                "/api/gestionnaire",
                                "/api/informationEmploi",
                                "/api/mandataire",
                                "/api/periodicitePaiementPrime",
                                "/api/periodiciteRemboursement",
                                "/api/personne",
                                "/api/questionnaireMedical",
                                "/api/report",
                                "/api/typeContrat",
                                "/api/dashboard",
                                "/api/souscription",
                                "/api/auth/**").permitAll()  // Notez l'utilisation de antMatchers()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt());

        return http.build();
    }

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

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        // tu peux ici ajouter un converter personnalisé si tu veux mapper les rôles de Keycloak
        return converter;
    }
}
