/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Racarmona.PruebaSpringJPA;


import com.Racarmona.PruebaSpringJPA.Repositorio.ICliente;
import com.Racarmona.PruebaSpringJPA.Repositorio.ILibros;

import java.util.ArrayList;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 *
 * @author Ricardo Carmona
 */
@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private ICliente rc;
    @Autowired
    private ILibros rl;
    @Autowired
    PasswordEncoder generatorPass;
    
    @GetMapping("/")
    public String Inicio(Model model) {
        passwordExample();
        return "redirect:/Autor/new";
    }
    
    
    public void passwordExample(){
        System.out.println(generatorPass.encode("avila"));
    }
    
}
