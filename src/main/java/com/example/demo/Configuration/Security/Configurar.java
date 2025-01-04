package com.example.demo.Configuration.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Configurar {

    @Autowired
    private UserDetailsService _userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bC;


    @Bean
    public AuthenticationManager autentica(AuthenticationConfiguration ac) throws Exception{
        return ac.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configura(HttpSecurity http) throws Exception{

        AuthenticationManagerBuilder aMB = http.getSharedObject(AuthenticationManagerBuilder.class);
        aMB.userDetailsService(_userDetailsService).passwordEncoder(bC);
        AuthenticationManager am = aMB.build();

        return http
				.csrf((csrf)->csrf.disable())
				.authenticationManager(am)
				.authorizeHttpRequests(t -> t
						.requestMatchers(HttpMethod.POST,"/Login").permitAll()
						.requestMatchers(HttpMethod.GET, "/productos/**").authenticated()
                        .and()
						.addFilterBefore(new FiltrarLogin("/Login",am),
								UsernamePasswordAuthenticationFilter.class)
						.addFilterBefore(new FiltrarToken(_userDetailsService),
								UsernamePasswordAuthenticationFilter.class)
						)
				.sessionManagement(s->s
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						)
				.build();
	    
    }  
}
