package com.vang.securitywithjwt.auth;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	public String generateToken(String username) {

		Map<String, Object> claim = new HashMap<String, Object>();
		return createToken(username, claim);
	}

	private String createToken(String username, Map<String, Object> claim) {

		return Jwts
				.builder()
				.claims(claim)
				.subject(username)
				.expiration(new Date(System.currentTimeMillis() * 120000))
				.signWith(getKey())
				.compact();
	}

	public boolean isValidateToken(String token, UserDetails userDetails) {

		return extractUsername(token).equals(userDetails.getUsername()) && extractToken(token, Claims::getExpiration).after(new Date());
	}

	public String extractUsername(String token) {

		return extractToken(token, Claims::getSubject);

	}

	public <T> T extractToken(String token, Function<Claims, T> claimReturn) {

		Claims claims = getClaim(token);
		return claimReturn.apply(claims);

	}

	private Claims getClaim(String token) {

		return Jwts
				.parser()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();

	}

	private Key getKey() {

		byte[] byteKey = Decoders.BASE64.decode("5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437");
		return Keys.hmacShaKeyFor(byteKey);
	}

}
