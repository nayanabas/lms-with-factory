package com.lms.Course.com.lms.Course.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final String SECRET_KEY = "learn_programming_yourself";

    public void getRoleFromToken(String authToken){
        String s = getClaimFromToken(authToken, Claims::getSubject);
        System.out.println(s);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }




    private Claims getAllClaimsFromToken(String authToken) {

        try {

                return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken).getBody();


        } catch (Exception e) {
            e.printStackTrace();
         //   LOGGER.error("Could not get all claims Token from passed token");
           // claims = null;
            return  null;
        }


    }

}
