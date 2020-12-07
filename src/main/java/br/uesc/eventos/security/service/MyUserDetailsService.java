package br.uesc.eventos.security.service;

import br.uesc.eventos.exception.CustomResponseException;
import br.uesc.eventos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioService.findByEmail(username);
    }

    public static UserDetails getAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()){
            return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            throw new CustomResponseException(HttpStatus.UNAUTHORIZED, "Nenhum usuário autenticado");
        }
    }
}
