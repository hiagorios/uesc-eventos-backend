package br.uesc.eventos.security.handlers;

import br.uesc.eventos.entity.Usuario;
import br.uesc.eventos.exception.CustomResponseException;
import br.uesc.eventos.security.util.Credentials;
import br.uesc.eventos.security.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwt;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwt) {
        this.authenticationManager = authenticationManager;
        this.jwt = jwt;

        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            LOGGER.debug("Attempting authentication");
            Credentials creds = new ObjectMapper().readValue(req.getInputStream(), Credentials.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );

        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomResponseException(HttpStatus.BAD_REQUEST, "Não foi possível ler as credenciais", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {

        String token = jwt.create((Usuario) auth.getPrincipal());

        res.addHeader("Authorization", "Bearer " + token);
        res.addHeader("access-control-expose-headers", "Authorization");
    }
}
