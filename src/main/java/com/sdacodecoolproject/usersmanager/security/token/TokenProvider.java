package com.sdacodecoolproject.usersmanager.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sdacodecoolproject.usersmanager.constant.SecurityConstant;
import com.sdacodecoolproject.usersmanager.model.CurrentUser;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class TokenProvider {
    @Value("jwt.secret")
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

    private static String[] getClaimsFromUser(CurrentUser currentUser) {

        return null;
    }

}
