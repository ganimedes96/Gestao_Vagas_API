package br.com.ganimedes.gestao_vagas.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTProvider {
  @Value("${security.token.secret}")
  private String secretKey;  

  public DecodedJWT validateToken (String token) {
   try {
    token = token.replace("Bearer ", "");
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var DecodedJWT = JWT.require(algorithm)
      .build()
      .verify(token);
    return DecodedJWT;
   } catch (JWTVerificationException ex) {
     ex.printStackTrace();
     return null;
   }
  }
}