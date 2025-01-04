package com.example.demo.Configuration.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Interfaz.IPersona;
import com.example.demo.Modelos.Persona;


@Service
public class DetalleUsuarioService implements UserDetailsService{

    @Autowired
    private IPersona _persona;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
       
        Optional<Persona> per = _persona.findBycorreo(correo);
        System.out.println(per.get().getNombres());

        if (per.isPresent()) {
            Persona objPersona = per.get();
            return User.builder()
                .username(objPersona.getNombres())
                .password(codificar().encode(objPersona.getPassword()))
                .roles("ADMIN")
                .build();
        }else{
            throw new UsernameNotFoundException("Usuario no existe..!");
        }
    }

    @Bean
    public BCryptPasswordEncoder codificar(){
        return new BCryptPasswordEncoder();
    }
    
}
