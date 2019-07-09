/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyectoCine.ProyectoCine.controlador;

import java.util.*;
import cl.proyectoCine.ProyectoCine.dao.AsientoDAO;
import cl.proyectoCine.ProyectoCine.dao.SalaDAO;
import cl.proyectoCine.ProyectoCine.modelo.Asiento;
import cl.proyectoCine.ProyectoCine.modelo.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ricardo
 */
@Controller
public class AsientoController {

    @Autowired
    private SalaDAO sDao;

    @Autowired
    private AsientoDAO aDao;

    @GetMapping("/asientos/crear/{id}")
    public String pageAsiento(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("asiento", new Asiento());
        model.addAttribute("nombreSala", sDao.findById(id).get().getNombreSala());
        model.addAttribute("idSala", sDao.findById(id).get().getId().intValue());
        model.addAttribute("listaAsientos", sDao.findById(id).get().getAsientoList());
        return "crearAsiento";
    }

    @PostMapping("/a/crear/{id}")
    public String nuevoA(@PathVariable("id") Integer id, @ModelAttribute Asiento asiento) {

        ArrayList<Asiento> asientos = (ArrayList<Asiento>) aDao.findAll();

        if (asientos.isEmpty()) {
            asiento.setId(1);
        } else {
            asiento.setId(asientos.get(asientos.size() - 1).getId() + 1);
        }

        
        System.out.println(asiento.getNombre());
        asiento.setOcupado(false);
        
        sDao.findById(id.intValue()).getAsientoList().add(asiento);
        
        

        
        return "redirect:/salas/ver";
    }
}
