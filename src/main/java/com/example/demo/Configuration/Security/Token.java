package com.example.demo.Configuration.Security;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Token {
    
    static final String firma = "HuEsAlHuEsAlHuEsAlHuEsAlHuEsAlHuEsAlHuEsAlHuEsAlHuEsAlHuEsAlHuEsAlHuEsAlHuEsAlHuEsAl"; 
    static final Key llave = new SecretKeySpec(Base64.getDecoder().decode(firma), SignatureAlgorithm.HS256.getJcaName());

    static void crearToken(HttpServletResponse res, String correo, String clave ){

        System.out.println(correo);
        String token = Jwts.builder()
				.setSubject(correo + "-" + clave)
				.setExpiration(new Date(System.currentTimeMillis()+(3*60000)))
				.signWith(llave)
				.compact();
		res.addHeader("Authorization", token);
    } 

    static Authentication validarToken(HttpServletRequest req, UserDetailsService detUs){

        String token = req.getHeader("Authorization");
        System.out.println(token);

        if (token != null) {
            
            String datos = Jwts.parser()
                            .setSigningKey(llave)
                            .parseClaimsJws(token)
                            .getBody()
                            .getSubject();
            int pos = datos.indexOf("-");
            System.out.println(datos);
            System.out.println(pos);
            String correo = datos.substring(0, pos);
            System.out.println(correo);
            String passw = datos.substring(pos+1);
            System.out.println(passw);

            UserDetails us = detUs.loadUserByUsername(correo);
            return new UsernamePasswordAuthenticationToken(correo, passw, us.getAuthorities());
        }
        return null;
    } 
}
