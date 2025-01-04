package com.example.demo.Configuration.Security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FiltrarToken extends OncePerRequestFilter {

    UserDetailsService detUs;

    public FiltrarToken(UserDetailsService detUs) {
		super();
		this.detUs = detUs;
	}

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication autoriza =  Token.validarToken(request, detUs);
        SecurityContextHolder.getContext().setAuthentication(autoriza);
        filterChain.doFilter(request, response);
    }
}
