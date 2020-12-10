package br.uesc.eventos.security.handlers;

import br.uesc.eventos.entity.Usuario;
import br.uesc.eventos.security.util.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final JWTUtil jwt;
    private final UserDetailsService userService;

    public JWTAuthorizationFilter(AuthenticationManager authManager, JWTUtil jwt, UserDetailsService userService) {
        super(authManager);
        this.jwt = jwt;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer")) {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(header.substring(7));
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(req, res);
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String email = jwt.getSubject(token);
        Usuario user = (Usuario) userService.loadUserByUsername(email);
        if (user != null) {
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        return null;
    }
}