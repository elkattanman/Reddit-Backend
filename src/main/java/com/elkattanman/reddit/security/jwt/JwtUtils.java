package com.elkattanman.reddit.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.elkattanman.reddit.util.JwtProperties;
import com.elkattanman.reddit.security.services.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;


@Component
@Slf4j
public class JwtUtils {

	public String generateJwtToken(Authentication authentication) {

		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

		return JWT.create()
				.withSubject((userPrincipal.getUsername()))
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
				.sign(HMAC512(JwtProperties.SECRET.getBytes()));
	}

	public String getUserNameFromJwtToken(String token) {
		return JWT.require(HMAC512(JwtProperties.SECRET.getBytes()))
				.build()
				.verify(token)
				.getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			JWT.require(HMAC512(JwtProperties.SECRET.getBytes()))
					.build()
					.verify(authToken);
			return true;
		} catch (TokenExpiredException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (AlgorithmMismatchException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (JWTVerificationException e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (SignatureGenerationException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
