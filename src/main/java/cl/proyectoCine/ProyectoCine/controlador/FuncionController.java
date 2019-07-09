/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyectoCine.ProyectoCine.controlador;

import cl.proyectoCine.ProyectoCine.dao.FuncionDAO;
import cl.proyectoCine.ProyectoCine.dao.PeliculaDAO;
import cl.proyectoCine.ProyectoCine.dao.SalaDAO;
import cl.proyectoCine.ProyectoCine.dao.TipoDAO;
import cl.proyectoCine.ProyectoCine.modelo.Funcion;
import cl.proyectoCine.ProyectoCine.modelo.Pelicula;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.Optional;
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
public class FuncionController {

    @Autowired
    private FuncionDAO fDao;

    @Autowired
    private PeliculaDAO pDao;

    @Autowired
    private SalaDAO sDao;

    @Autowired
    private TipoDAO tDao;

    @GetMapping("/funciones/ver")
    public String verFuncion(Model model) {

        model.addAttribute("funciones", fDao.findAll());

        return "verFunciones";
    }

    @RequestMapping("/funciones/crear")
    public String crearF(Model model) {
        model.addAttribute("funcion", new Funcion());
        model.addAttribute("peliculas", pDao.findAll());
        model.addAttribute("salas", sDao.findAll());
        model.addAttribute("tiposFuncion", tDao.findAll());
        return "crearFuncion";
    }

    @PostMapping("/funcion/crear")
    public String funcionCrear(@ModelAttribute Funcion funcion) {

        ArrayList<Funcion> funciones = (ArrayList<Funcion>) fDao.findAll();

        if (funciones.isEmpty()) {
            funcion.setId(1);
        } else {
            funcion.setId(funciones.get(funciones.size() - 1).getId() + 1);

        }

        //campo pelicula = funcion.
        System.out.println(funcion.getPeliculaid());
        System.out.println(funcion.getFecha());
        System.out.println(funcion.getSalaid());

        fDao.save(funcion);

        return "redirect:/funciones/ver";

    }

    //Ver las funciones de la pelicula con id = 1,2,3 ;
    @GetMapping("/funciones/ver/{id}")
    public String verFuncion(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("funcionesById", pDao.findById(id).get().getFuncionList());

        return "funcionPelicula";
    }

    
    //abrir la sala de la funcion con id= n;
    @GetMapping("venta/funcion/{id}")
    public String pageSalaF(@PathVariable("id") Integer id,Model model) {

        model.addAttribute("asientos", fDao.findById(id).get().getSalaid().getAsientoList());
        model.addAttribute("funcion", fDao.findById(id).get().getPeliculaid().getTitulo());
      
        

        return "redirect:/venta/ver/" + id;

    }

}
