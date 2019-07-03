/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyectoCine.ProyectoCine.controlador;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import cl.proyectoCine.ProyectoCine.dao.CategoriaDAO;
import cl.proyectoCine.ProyectoCine.modelo.Categoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ricardo
 */
@Controller
public class CategoriaController {

    @Autowired
    private CategoriaDAO cDAO;

    @GetMapping("/verCategorias")
    public String verCartelera(Model model) {

        model.addAttribute("categorias", cDAO.findAll());
        return "verCategorias";
    }

    @GetMapping("/crearCategoria")
    public String createIdioma(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "crearCategoria";
    }

    @PostMapping("/categoria/crear")
    public String createCategoria(@ModelAttribute Categoria categoria) {
        ArrayList<Categoria> categorias = (ArrayList<Categoria>) cDAO.findAll();

        if (!categorias.isEmpty()) {
            categoria.setId(categorias.get(categorias.size() - 1).getId() + 1);
        } else {
            categoria.setId(1);
        }

        cDAO.save(categoria);

        return "redirect:/verCategorias";
    }

    @DeleteMapping("/deleteCategoria/{id}")
    @ResponseBody
    public String deleteCategoria(@PathVariable("id") int id) {

        if (!cDAO.findById(id).isPresent()) {

            return "Categoria no existe";
        }
        Optional<Categoria> currentCategoria = cDAO.findById(id);

        cDAO.deleteById(id);
        return "Categoria " + currentCategoria.get().getDescripcion() + " eliminada";
    }

}
