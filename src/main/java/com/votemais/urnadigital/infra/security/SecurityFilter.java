package com.votemais.urnadigital.infra.security;

import com.votemais.urnadigital.repository.interfaces.AssociadoRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    AssociadoRepository associadoRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = getTokenRequest(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        var subject = this.tokenService.VerifyJWT(token);
        UserDetails associado = this.associadoRepository.findByLogin(subject);

        var authentication = new UsernamePasswordAuthenticationToken(associado,null,associado.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    private String getTokenRequest(HttpServletRequest request) {

        var auth = request.getHeader("Authorization");
        if (auth == null) { return null;}

        return auth.replace("Bearer ","");

    }


}
