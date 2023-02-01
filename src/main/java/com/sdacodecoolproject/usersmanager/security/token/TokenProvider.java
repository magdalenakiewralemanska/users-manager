package com.sdacodecoolproject.usersmanager.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sdacodecoolproject.usersmanager.constant.SecurityConstant;
import com.sdacodecoolproject.usersmanager.model.CurrentUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TokenProvider {
    @Value(value = "jwt.secret")
    private static String secret;

    public String generateToken(CurrentUser currentUser){
        String[] claims = getClaimsFromUser(currentUser);
        return JWT.create().withIssuer(SecurityConstant.GET_ARRAYS_LLC)
                .withAudience(SecurityConstant.GET_ARRAYS_ADMINISTRATION)
                .withIssuedAt(new Date())
                .withSubject(currentUser.getUsername())
                .withArrayClaim(SecurityConstant.AUTHORITIES, claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(secret.getBytes()));
    }

    public List<GrantedAuthority> getAuthorities(String token){
        List<String> claims = getClaimsFromToken(token);
        return claims.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private static List<String> getClaimsFromToken(String token) {
        JWTVerifier verifier = getVerifier();
        return verifier.verify(token)
                .getClaim(SecurityConstant.AUTHORITIES)
                .asList(String.class);
    }

    private static JWTVerifier getVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            verifier = JWT.require(algorithm).withIssuer(SecurityConstant.GET_ARRAYS_LLC).build();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(SecurityConstant.NOT_VERIFIED_TOKEN);
        }
        return verifier;
    }

    private static String[] getClaimsFromUser(CurrentUser currentUser) {
        List<String> authorities = new ArrayList<>();
        for(GrantedAuthority grantedAuthority: currentUser.getAuthorities()){
            authorities.add(grantedAuthority.getAuthority());
        }
        return authorities.toArray(new String[0]);
    }

}
