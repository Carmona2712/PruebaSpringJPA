/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Racarmona.PruebaSpringJPA.Controladores;

import com.Racarmona.PruebaSpringJPA.Modelo.Administrador;
import com.Racarmona.PruebaSpringJPA.Modelo.Autor;
import com.Racarmona.PruebaSpringJPA.Repositorio.IAutores;
import com.Racarmona.PruebaSpringJPA.Repositorio.IUsers;
import com.Racarmona.PruebaSpringJPA.Utils.Message;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/Autor")
public class AutorController {

    @Autowired
    private IAutores ra;
    @Autowired
    private IUsers ru;

    @Transactional
    @RequestMapping("/new")
    public String CreateAuthor(Model model, Authentication auth) {
        model.addAttribute("autor", new Autor());
        model.addAttribute("autores", ra.findAll());
        // model.addAttribute("user",admin);
        return "NewAutor";
    }

    @GetMapping("/view/{id}")
    public String viewAutor(@PathVariable("id") int id, Model model) {
        Autor a = ra.findById(id).get();
        model.addAttribute("autor", a);
        return "NewAutor";
    }

    @GetMapping("/update/{id}")
    public String updateAutor(@PathVariable("id") int id, Model model) {
        Autor a = ra.findById(id).get();
        System.out.println("ID recibido : " + a.getId());
        model.addAttribute("autores", ra.findAll());
        model.addAttribute("autor", a);
        return "NewAutor";
    }

    @GetMapping("/delete/{id}")
    public String deleteAutor(@PathVariable("id") int id, Model model) {
        Message msj = null;
        if (ra.existsById(id)) {
            ra.deleteById(id);
            msj = new Message("Se elimino correctmente", "success");
            model.addAttribute("autores", ra.findAll());
            model.addAttribute("autor", new Autor());
            model.addAttribute("message", msj);
            return "NewAutor";
        } else {
            msj = new Message("Error al eliminar", "danger");
            model.addAttribute("autores", ra.findAll());
            model.addAttribute("autor", new Autor());
            model.addAttribute("message", msj);
            return "NewAutor";
        }
    }

    @PostMapping(value = "/save")
    public String guardarAutor(@ModelAttribute Autor a, Model model, RedirectAttributes redirectAttributes) {
        Message msj = null;
        System.out.println("ID recibido : " + a.getId());
        try {
            if (ra.existsById(a.getId())) {
                ra.save(a);
                msj = new Message("Autor Actualizado correctamente", "success");
            } else {
                ra.save(a);
                msj = new Message("Autor registrado correctamente", "success");
            }
        } catch (Exception ex) {
            msj = new Message("Error al registrar autor : " + ex.getMessage(), "danger");
        }
        String titulo = "Registro de Autor";
        model.addAttribute("autor", new Autor());
        model.addAttribute("titulo", titulo);
        model.addAttribute("autores", ra.findAll());
        model.addAttribute("message", msj);
        return "NewAutor";
    }

    private ModelAndView autorView() {
        ModelAndView mav = new ModelAndView("autor");
        mav.addObject("autor", new Autor());
        mav.addObject("autores", ra.findAll());
        return mav;
    }

}
