/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Racarmona.PruebaSpringJPA.Security;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Ricardo Carmona
 */
@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select usuario, pass, estado from administrador WHERE usuario = ?")
                .authoritiesByUsernameQuery("select a.usuario,r.nombre from administrador a INNER JOIN rol r on a.idRol = r.id and a.usuario = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/assets/**", "/app-assets/**", "/gulp-task/**", "/src/**", "starter-kit/**").permitAll()// Los recursos estáticos no requieren autenticación
                .antMatchers("/","/login","/logout").permitAll() // Las vistas públicas no requieren autenticación
                .anyRequest().authenticated() // Todas las demás URLs de la Aplicación requieren autenticación
                .and().formLogin().loginPage("/login").permitAll();   // El formulario de Login no requiere autenticacion 

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    
    
}
