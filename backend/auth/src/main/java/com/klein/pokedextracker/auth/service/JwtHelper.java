package com.klein.pokedextracker.auth.service;

import com.klein.pokedextracker.config.service.IConfigService;
import com.klein.pokedextracker.user.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtHelper {

    private final IConfigService configService;

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("userId", String.class);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(
                Keys.hmacShaKeyFor(configService.getString("api.jwt.secret", "")
                        .getBytes(StandardCharsets.UTF_8)
                )).build();
        return parser.parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        boolean tokenExpirationEnabled = configService.getBool("api.jwt.expiration.enabled", true);
        if (!tokenExpirationEnabled) {
            return false;
        }
        try {
            return extractExpiration(token).before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public String extractUserIdFromTokenInRequest(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        return extractUserId(token);
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwtToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        throw new IllegalArgumentException("JWT token not found in request.");
    }

    public String generateToken(UserModel userModel) {
        String jwtSecret = configService.getString("api.jwt.secret", "");

        Date currentDate = new Date();
        Long tokenLifetimeLength = configService.getLong("api.jwt.expiration", 600000L);
        Date expiryDate = new Date(currentDate.getTime() + tokenLifetimeLength);

        boolean isTokenExpirationEnabled = configService.getBool("api.jwt.expiration.enabled", true);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userModel.getUserId());
        claims.put("username", userModel.getUsername());

        return Jwts.builder()
                .setIssuedAt(currentDate)
                .setClaims(claims)
                .setExpiration(isTokenExpirationEnabled ? expiryDate : null)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}
