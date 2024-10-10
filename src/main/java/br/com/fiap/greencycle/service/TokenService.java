package br.com.fiap.greencycle.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.fiap.greencycle.model.User;

@Service
public class TokenService {
	
	Algorithm algo = Algorithm.HMAC256("fiap-secreto");
	
	public String gerarToken(User usuario) {
		
		String token = JWT
				.create()
				.withIssuer("fiap")
				.withSubject(usuario.getUsername())
				.withExpiresAt(gerarDataDeExpiracao())
				.sign(algo);
		
		return token;
		
	}
	
	public String validarToken(String token) {
		
		try {
			return JWT
					.require(algo)
					.withIssuer("fiap")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException exception) {
			return "";
		}
		
	}
	
	private Instant gerarDataDeExpiracao() {
		return LocalDateTime
				.now()
				.plusHours(2)
				.toInstant(ZoneOffset.of("-03:00"));
	}

}
