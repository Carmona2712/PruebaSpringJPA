/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Racarmona.PruebaSpringJPA.Controladores;

import com.Racarmona.PruebaSpringJPA.Modelo.Abastecimiento;
import com.Racarmona.PruebaSpringJPA.Repositorio.IAbastecimiento;
import com.Racarmona.PruebaSpringJPA.Utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Ricardo Carmona
 */
@Controller
public class AbastecimientoController {
    
    @Autowired
    private IAbastecimiento rab;
    
    
    public String RegistrarAbastecimiento(@PathVariable("abastecimiento") Abastecimiento aba,Model model){
        Message msg = null;
        try{
            rab.save(aba);
            msg = new Message("Se registro el abastecimiento correctamente","success");
        }catch(Exception ex){
            msg = new Message("Se registro el abastecimiento correctamente","success");
        }
        model.addAttribute("message",msg);
        return "/";
    }
    
    
    
}
