package com.mehmetkicirti.blogapplication.utility.security;

import com.mehmetkicirti.blogapplication.entity.concrete.User;
import com.mehmetkicirti.blogapplication.utility.constant.ExceptionConstants;
import com.mehmetkicirti.blogapplication.utility.exception.BlogAPIException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; //24h

    @Value("${app.jwt.secretkey}")
    private String secretKey;

    @Value("${app.jwt.issuer}")
    private String issuer;

    public String generateToken(User user){
        /*
        .claim("roles", user.getRoles().toString())
                        .setSubject(user.getId() + "," + user.getEmail()) => Yazdığımız servisten döndürüp getiricez.
         */
        return Jwts.builder()
                .claim("roles", user.getRoles().toString())
                .setIssuer(issuer)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean isValidToken(String token){
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token);
            return true;
        }catch (SignatureException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, ExceptionConstants.INVALID_SIGNATURE);
        }catch (MalformedJwtException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, ExceptionConstants.INVALID_TOKEN);
        }catch (ExpiredJwtException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, ExceptionConstants.EXPIRED_TOKEN);
        }catch (UnsupportedJwtException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, ExceptionConstants.UNSUPPORTED_TOKEN);
        }catch (IllegalArgumentException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, ExceptionConstants.CLAIM_EMPTY);
        }
    }

    public String getSubject(String token){
        return parseClaims(token).getSubject();
    }

    public Claims parseClaims(String token){
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
