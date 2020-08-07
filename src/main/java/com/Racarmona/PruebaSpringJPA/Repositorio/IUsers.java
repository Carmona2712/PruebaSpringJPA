/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Racarmona.PruebaSpringJPA.Repositorio;

import com.Racarmona.PruebaSpringJPA.Modelo.Administrador;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Ricardo Carmona
 */
public interface IUsers extends CrudRepository<Administrador,String>{
    
    
    
    
    
}
