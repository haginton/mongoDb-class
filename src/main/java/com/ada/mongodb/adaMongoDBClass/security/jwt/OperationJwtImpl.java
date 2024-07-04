package com.ada.mongodb.adaMongoDBClass.security.jwt;

import com.ada.mongodb.adaMongoDBClass.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;

@Component
public class OperationJwtImpl implements OperationJwt{

    @Value("${jwt.secret-key}")
    private String secret_key;

    @Override
    public String generateJwt(User user, Calendar durationToken) {
        return Jwts.builder()
                .subject(user.getIdUser())
                .claim("username", user.getUsername())
                .issuedAt(new Date())
                .expiration(durationToken.getTime())
                .signWith(generateKey(), Jwts.SIG.HS256)
                .compact()
                ;
    }

    private SecretKey generateKey(){
        byte[] passwordDecoded = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(passwordDecoded);
    }
}
