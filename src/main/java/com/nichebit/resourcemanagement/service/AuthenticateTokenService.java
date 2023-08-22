package com.nichebit.resourcemanagement.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthenticateTokenService {

	private static final String SECRET = "01A2B3C4D5E6F701A2B3C4D5E6F701A2B3C4D5E6F701A2B3C4D5E6F701A2B3C4";

	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsresolver) {
		final Claims claims = extractAllClaims(token);
		return claimsresolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {

		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		return createTokent(claims, username);
	}

	private String createTokent(Map<String, Object> claims, String username) {

		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 120))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	private Key getSignKey() {
		byte[] keybytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keybytes);
	}

}
