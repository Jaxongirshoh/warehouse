package dev.wisespirit.warehouse.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private final String SECRET_KEY = "7134743777217A25432A462D4A614E645267556B58703272357538782F413F44";
    private final Key SIGNING_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();

        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*30))
                .and()
                .signWith(SIGNING_KEY)
                .compact();
    }


}
