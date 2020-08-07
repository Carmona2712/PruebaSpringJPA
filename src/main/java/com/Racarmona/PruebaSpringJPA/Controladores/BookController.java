/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Racarmona.PruebaSpringJPA.Controladores;

import com.Racarmona.PruebaSpringJPA.Modelo.Libro;
import com.Racarmona.PruebaSpringJPA.Repositorio.IAutores;
import com.Racarmona.PruebaSpringJPA.Repositorio.IEditoriales;
import com.Racarmona.PruebaSpringJPA.Repositorio.ILibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Ricardo Carmona
 */
@Controller
@RequestMapping(value = "/Book")
public class BookController {

    @Autowired
    private ILibros rl;
    private IAutores ra;
    private IEditoriales re;

    @RequestMapping("/new")
    public ModelAndView newBook() {
        ModelAndView view = new ModelAndView("newBook");
        view.addObject("autores", ra.findAll());
        view.addObject("libro", new Libro());
        view.addObject("editoriales", re.findAll());
        return view;
        //return "newBook";
    }

    @Transactional
    @PostMapping("/save")
    public String SaveBook(Libro l){
        try {
            rl.save(l);
        } catch (Exception ex) {
            return "redirect:book/bookList";
        }
        return "/newBook";
    }

    @RequestMapping("/list")
    public String bookList() {
        return "book/bookList";
    }

}
