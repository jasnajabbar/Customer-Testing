package customer.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

@Service
public class JwtService {

    private static final String SECRET =
            "/Ym7W4+bRxzn4lQFHP5xo50rk05bl3XIQZA9HgQA++k=";

    public String generateToken(String userName){
        return Jwts.builder()
//                .claims(claims)
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ 1000*60*10))
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
            return extractClaim(token,Claims::getSubject);
        }

    public <T> T extractClaim(String token, Function<Claims,T>  claimResolver){
        Claims claims=extractAllClaim(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaim(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean checkTokenExpiry(String token){
        return extractExpiry(token).before(new Date());
    }

    private Date extractExpiry(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

        String username = extractUsername(token);
        return username.equals(userDetails.getUsername())&& !checkTokenExpiry(token);
    }
}
