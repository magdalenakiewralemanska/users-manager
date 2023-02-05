package com.sdacodecoolproject.usersmanager.user.login.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sdacodecoolproject.usersmanager.application.constant.SecurityConstant;
import com.sdacodecoolproject.usersmanager.user.login.CurrentUser;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class TokenProvider {
    @Value("${jwt.secret}")
    private String secret;

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

    private String[] getClaimsFromUser(CurrentUser currentUser) {
        List<String> authorities = new ArrayList<>();
        for(GrantedAuthority grantedAuthority: currentUser.getAuthorities()){
            authorities.add(grantedAuthority.getAuthority());
        }
        return authorities.toArray(new String[0]);
    }

    private List<String> getClaimsFromToken(String token) {
        JWTVerifier verifier = getVerifier();
        return verifier.verify(token)
                .getClaim(SecurityConstant.AUTHORITIES)
                .asList(String.class);
    }

    public List<GrantedAuthority> getAuthorities(String token){
        List<String> claims = getClaimsFromToken(token);
        return claims.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request){
        UsernamePasswordAuthenticationToken usernamePassAuthToken =
                new UsernamePasswordAuthenticationToken(username, null, authorities);
        usernamePassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePassAuthToken;
    }

    private JWTVerifier getVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            verifier = JWT.require(algorithm).withIssuer(SecurityConstant.GET_ARRAYS_LLC).build();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(SecurityConstant.NOT_VERIFIED_TOKEN);
        }
        return verifier;
    }

    public boolean isTokenValid(String username, String token){
        JWTVerifier verifier = getVerifier();
        return StringUtils.isNoneEmpty(username) && !isTokenExpired(token, verifier);
    }

    private boolean isTokenExpired(String token, JWTVerifier verifier) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    public String getSubject(String token){
        JWTVerifier verifier = getVerifier();
        return verifier.verify(token).getSubject();
    }
}
