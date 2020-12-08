package br.uesc.eventos.security.util;

import br.uesc.eventos.entity.Permissao;
import br.uesc.eventos.entity.Usuario;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JWTUtil {

    @Value("${auth.jwt.secret}")
    private String secret;

    @Value("${auth.jwt.expiration}")
    private Long expiration;

    public String create(Usuario usuario) {
        return JWT.create()
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withClaim("nome", usuario.getNome())
                .withClaim("permissions", usuario.getPerfil().getPermissoes().stream().map(Permissao::getKey).collect(Collectors.toList()))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(HMAC512(secret.getBytes()));
    }

    public String getSubject(String token) {
        return JWT.require(HMAC512(secret.getBytes()))
                .build()
                .verify(token.replace("Bearer ", ""))
                .getSubject();
    }

}
