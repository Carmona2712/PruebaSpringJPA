/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Racarmona.PruebaSpringJPA.Controladores;

import com.Racarmona.PruebaSpringJPA.Modelo.Administrador;
import com.Racarmona.PruebaSpringJPA.Modelo.Autor;
import com.Racarmona.PruebaSpringJPA.Repositorio.IUsers;
import com.Racarmona.PruebaSpringJPA.Utils.Message;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Ricardo Carmona
 */
@Controller
@Slf4j
public class UserController {

    @Autowired
    private IUsers ru;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/validation")
    public String Validation(@RequestParam("user") String user, @RequestParam("password") String pass, RedirectAttributes redirectAttributes, HttpSession session, Model model) {
        String pagina = "/";
        Message mens = null;
        Administrador ad = null;
        if (ru.existsById(user)) {
            ad = ru.findById(user).get();
            System.out.println("pass del sistema : "+ad.getPass());
            System.out.println("pass encriptado : "+passwordEncoder.encode(pass));
            if (ad.getPass().equals(passwordEncoder.encode("456"))) {
                
                session.setAttribute("user", ad);
                return "redirect:/Autor/new";
            } 
        } else {
            pagina = "/";
            mens = new Message();
            mens.setStatus("error");
            mens.setText("Nombre de usuario o contraseña incorrectos");
        }
        redirectAttributes.addFlashAttribute("message", mens);
        return "Login";
    }
    
   @GetMapping("/login")
   public String Login(){
       return "Login";
   }
   
   @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/login";
    }

    @GetMapping("/demo-bcrypt")
    public String pruebaBcrypt() {
        String password = "456";
        String encriptado = passwordEncoder.encode(password);
        System.out.println("Password encriptado: " + encriptado);
        return "redirect:/Autor/new";
    }

}
