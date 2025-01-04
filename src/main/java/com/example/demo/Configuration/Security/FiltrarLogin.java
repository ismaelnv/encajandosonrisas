package com.example.demo.Configuration.Security;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.Modelos.Persona;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FiltrarLogin extends AbstractAuthenticationProcessingFilter{

    private Persona persona;

    protected FiltrarLogin(String url, AuthenticationManager autoriza) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(autoriza);	
	}

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        InputStream mensaje = request.getInputStream();
        persona = new ObjectMapper().readValue(mensaje, Persona.class);
        
        return getAuthenticationManager().authenticate(
            new UsernamePasswordAuthenticationToken(persona.getCorreo(), persona.getPassword())
        );
    }

    @Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		Token.crearToken(response, persona.getCorreo(), persona.getPassword());
	}
    
}
