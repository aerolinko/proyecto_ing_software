package com.latmedina.Almacen_Datos.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Cambia esto si tu prefijo de API es diferente
                .allowedOrigins("http://localhost:5173") // Cambia esto si tu frontend corre en otro puerto
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}