package controller.command.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.util.Date;

public class JwtManager {
    private static final String CLAIM_ROLE = "authorities";
    private static final String CLAIM_USER = "user_id";
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final SecretKey SECRET_KEY = MacProvider.generateKey(SIGNATURE_ALGORITHM);
    private static final TemporalAmount TOKEN_VALIDITY = Duration.ofHours(4L);

    public String createToken(final String subject, final String role, Long userId) {
        final Instant now = Instant.now();
        final Date expiryDate = Date.from(now.plus(TOKEN_VALIDITY));
        return Jwts.builder()
                .setSubject(subject)
                .claim(CLAIM_ROLE, role)
                .claim(CLAIM_USER, userId)
                .setExpiration(expiryDate)
                .setIssuedAt(Date.from(now))
                .signWith(SIGNATURE_ALGORITHM, SECRET_KEY)
                .compact();
    }

    public Jws<Claims> parseToken(final String compactToken)
            throws ExpiredJwtException,
            UnsupportedJwtException,
            MalformedJwtException,
            SignatureException,
            IllegalArgumentException {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(compactToken);
    }

}
