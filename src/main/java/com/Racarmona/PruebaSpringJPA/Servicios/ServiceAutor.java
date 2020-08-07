/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Racarmona.PruebaSpringJPA.Servicios;

import com.Racarmona.PruebaSpringJPA.Modelo.Autor;
import com.Racarmona.PruebaSpringJPA.Repositorio.IAutores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Ricardo Carmona
 */
public class ServiceAutor{
    
    @Autowired
    private IAutores ra;
    
    
    @Query("Select * from autores a where a.id = :id")
    public boolean registerAutor(Autor a,String id){
        if(ra.save(a)!=null){
            return true;
        }else{
            return false;
        }
    }
    
    
    
    
    
    
}
