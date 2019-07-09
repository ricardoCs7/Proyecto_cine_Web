/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyectoCine.ProyectoCine.controlador;

import cl.proyectoCine.ProyectoCine.dao.CredencialDAO;
import cl.proyectoCine.ProyectoCine.dao.UsuarioDAO;
import cl.proyectoCine.ProyectoCine.modelo.Credencial;
import cl.proyectoCine.ProyectoCine.modelo.Usuario;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ricardo
 */
@Controller
public class LoginController {

    @Autowired
    private UsuarioDAO uDAO;

    @Autowired
    private CredencialDAO cDao;

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("credencial", new Credencial());
        return "login";

    }

    @PostMapping("login")
    public String log(@ModelAttribute Credencial c, HttpServletRequest request,Model model) {

        Credencial adminBd = cDao.findByUserNameAndPassword(c.getUserName(), c.getPassword());

        if (adminBd != null) {
            request.getSession().setAttribute("usuarioLogueado", adminBd);
            return "adminApp";
        } else {
            model.addAttribute("credencial", new Credencial());
            model.addAttribute("error", true);
            return "login";

        }

    }
}
