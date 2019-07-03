/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyectoCine.ProyectoCine.dao;

import cl.proyectoCine.ProyectoCine.modelo.Funcion;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Ricardo
 */
public interface FuncionDAO extends CrudRepository<Funcion, Integer>{

    public Funcion findById(int id);

    public Object findAllById(int id);
    
}
 
    
    

