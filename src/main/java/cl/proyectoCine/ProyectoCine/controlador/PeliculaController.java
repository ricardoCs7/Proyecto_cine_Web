/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyectoCine.ProyectoCine.controlador;

import cl.proyectoCine.ProyectoCine.dao.CategoriaDAO;
import cl.proyectoCine.ProyectoCine.dao.IdiomaDAO;
import cl.proyectoCine.ProyectoCine.dao.PeliculaDAO;
import cl.proyectoCine.ProyectoCine.modelo.Pelicula;
import javax.validation.constraints.AssertFalse.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ricardo
 */
@Controller
public class PeliculaController {

    @Autowired
    private PeliculaDAO pDAO;

    @Autowired
    private IdiomaDAO iDAO;

    @Autowired
    private CategoriaDAO cDAO;

    @RequestMapping("/subirPelicula")
    public String subirPeliPage(Model model) {
        model.addAttribute("pelicula", new Pelicula());
        model.addAttribute("idiomas", iDAO.findAll());
        model.addAttribute("categorias", cDAO.findAll());
        return "subirPelicula";
    }

    
    
    
    

    @GetMapping("/cartelera")
    public String verCartelera(Model model) {

        Iterable<Pelicula> lista = pDAO.findAll();

        model.addAttribute("listaPeliculas", lista);
        return "cartelera";
    }

    @DeleteMapping("eliminarPelicula/{id}")
    public String deleteIdioma(@PathVariable("id") Integer id) {
        pDAO.deleteById(id);
        return "redirect:/ cartelera";
        
    }

    @PostMapping("/pelicula/subir")
    public String subirPeli(@ModelAttribute Pelicula pelicula) {

        ArrayList<Pelicula> peliculas = (ArrayList<Pelicula>) pDAO.findAll();

        if (peliculas.isEmpty()) {
            pelicula.setId(1);
        }else
        
        pelicula.setId(peliculas.get(peliculas.size() - 1).getId() + 1);
        System.out.println(pelicula.getTitulo());
        System.out.println(pelicula.getCategoriaid());
        System.out.println(pelicula.getUrl());
        System.out.println(pelicula.getIdiomaid());

        pDAO.save(pelicula);

        return "redirect:/cartelera";
    }

}
