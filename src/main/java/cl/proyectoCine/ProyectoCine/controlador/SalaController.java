/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyectoCine.ProyectoCine.controlador;

import cl.proyectoCine.ProyectoCine.dao.AsientoDAO;
import cl.proyectoCine.ProyectoCine.dao.FuncionDAO;
import cl.proyectoCine.ProyectoCine.dao.SalaDAO;
import cl.proyectoCine.ProyectoCine.modelo.Asiento;
import cl.proyectoCine.ProyectoCine.modelo.Sala;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SalaController {

    @Autowired
    private SalaDAO sDao;

    @Autowired
    private AsientoDAO aDao;

    @GetMapping("/salas/ver")
    public String verSalas(Model model) {
        model.addAttribute("salas", sDao.findAll());
        model.addAttribute("asientos", aDao.findAll());
        return "listaSalas";
    }

    @RequestMapping("/sala/crear")
    public String page(Model model) {
        model.addAttribute("sala", new Sala());

        
        return "crearSala";
    }

    @PostMapping("/salaForm")
    public String crearSala(@ModelAttribute Sala sala) {

        ArrayList<Sala> salas = (ArrayList<Sala>) sDao.findAll();
        if (salas.isEmpty()) {
            sala.setId(1);
        } else {
            sala.setId(salas.get(salas.size() - 1).getId() + 1);
        }

        System.out.println(sala.getNombreSala());

        sDao.save(sala);
        return "redirect:/salas/ver";

    }

    @GetMapping("/salas/ver/{id}")
    public String verSalas(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("asientos", sDao.findById(id).get().getAsientoList());
        model.addAttribute("sala", sDao.findById(id).get().getNombreSala());

        return "verSala";

    }

}
