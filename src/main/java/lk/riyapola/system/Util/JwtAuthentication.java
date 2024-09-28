package lk.riyapola.system.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.riyapola.system.entity.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtAuthentication {

    private final String jwtSecret = "2bSU7ttILA8FdHYfiQdrYyW0sCxJ3QebJgDEbCG8i9E=";

    private final int jwtExpirationInMs = 86400000;

    public String generateJwtAuthentication(User user) {

        return Jwts.builder()
                .subject((user.getEmail()))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationInMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Key key (){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken) {

        String jwtToken = authToken.substring("Bearer".length());

        try {
            Jwts.parser().setSigningKey(key()).build().parse(jwtToken);
            return true;
        }catch (Exception e){
            System.out.println("Invalid JWT token");
        }
        return false;
    }
}
