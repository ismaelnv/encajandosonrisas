package com.example.demo.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.NonNull;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        
        registry.addResourceHandler("/imagenes/**")
                .addResourceLocations("file:src/main/resources/img/")
                .setCachePeriod(0);
    }
    
}
