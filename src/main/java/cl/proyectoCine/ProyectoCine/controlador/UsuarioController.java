/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyectoCine.ProyectoCine.controlador;

import cl.proyectoCine.ProyectoCine.dao.UsuarioDAO;
import cl.proyectoCine.ProyectoCine.modelo.Usuario;
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
public class UsuarioController {

    @Autowired
    UsuarioDAO uDAO;

    @GetMapping("/registroUser")
    public String registroUserPage(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registroUser";

    }

    @GetMapping("/getUsuarios")
    @ResponseBody
    public Iterable<Usuario> getUsers() {

        return uDAO.findAll();

    }

    @DeleteMapping("/deleteUsuario/{id}")
    @ResponseBody
    public String borrarUsuario(@PathVariable("id") Integer id) {

        if (!uDAO.findById(id).isPresent()) {
            return "Usuario " + id + " no existe";
        }
        uDAO.deleteById(id);
        return "Usuario " + id + " eliminado";

    }

    @GetMapping("/usuario/{id}")
    @ResponseBody
    public Optional<Usuario> getUsuarioPorId(@PathVariable("id") Integer id) {
        if (!uDAO.findById(id).isPresent()) {
            return null;
        } else {
            return uDAO.findById(id);
        }

    }

    @PostMapping("/usuario/crear")
    public String createUsaurios(@ModelAttribute Usuario usuario) {

        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) uDAO.findAll();
        
        if (usuarios.isEmpty()) {
            usuario.setId(1);
        }else

        usuario.setId(usuarios.get(usuarios.size() - 1).getId() + 1);
        System.out.println(usuario.getUserName());
        System.out.println(usuario.getPassword());
        System.out.println(usuario.getTipo());
        System.out.println(usuario.getNombre());
        System.out.println(usuario.getApellido());
        System.out.println(usuario.getEmail());

        uDAO.save(usuario);

        return "index";
    }

}
