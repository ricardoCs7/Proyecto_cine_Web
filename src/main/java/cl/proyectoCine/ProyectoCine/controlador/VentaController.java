/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyectoCine.ProyectoCine.controlador;

import cl.proyectoCine.ProyectoCine.dao.FuncionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ricardo
 */
@Controller
public class VentaController {
    
    @Autowired
    private FuncionDAO fDao;

    @RequestMapping("/venta/ver/{id}")
    public String page(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("asientos", fDao.findById(id).get().getSalaid().getAsientoList());
        model.addAttribute("pelicula", fDao.findById(id).get().getPeliculaid().getTitulo());
        model.addAttribute("precio",fDao.findById(id).get().getTipoid().getPrecio().toString());
        model.addAttribute("fecha", fDao.findById(id).get().getFecha());
        
        int precio = fDao.findById(id).get().getTipoid().getPrecio();
        
//        int total = precio* "numAsientos";
        
        return "venta";
    }

}
