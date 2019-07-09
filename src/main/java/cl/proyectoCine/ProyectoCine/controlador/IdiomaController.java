package cl.proyectoCine.ProyectoCine.controlador;

import cl.proyectoCine.ProyectoCine.dao.IdiomaDAO;
import cl.proyectoCine.ProyectoCine.modelo.Idioma;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ricardo
 */
@Controller
public class IdiomaController {

    @Autowired
    IdiomaDAO iDAO;

    @GetMapping("/idiomas/ver")
    public String verIdioma(Model model) {

        model.addAttribute("idiomas", iDAO.findAll());
        return "verIdiomas";
    }

    @GetMapping("/idiomas/crear")
    public String createIdioma(Model model) {
        model.addAttribute("idioma", new Idioma());
        return "crearIdioma";
    }

    @PostMapping("/idiomaForm")
    public String createIdioma(@ModelAttribute Idioma idioma) {
        ArrayList<Idioma> idiomas = (ArrayList<Idioma>) iDAO.findAll();

        if (idiomas.isEmpty()) {
            idioma.setId(1);
        } else {
            idioma.setId(idiomas.get(idiomas.size() - 1).getId() + 1);
        }

        System.out.println(idioma.getDescripcion());

        iDAO.save(idioma);

//        if (idiomas.size() != 0) {
//            idioma.setId(idiomas.get(idiomas.size() - 1).getId() + 1);
//        } else {
//            idioma.setId(1);
//        }
//
//        if (iDAO.findById(idioma.getId()).isPresent()) {
//            return null;
//        }
        return "redirect:/idiomas/ver";
    }

//   @PutMapping("/editarIdioma/{id}"){
//     public String editIdioma(@PathVariable("id") int id) {
//    
//}
    @DeleteMapping("/idioma/eliminar/{id}")
    public String deleteIdioma(@PathVariable("id") Integer id) {

        iDAO.deleteById(id.intValue());

        return "redirect:/idiomas/ver";
    }

}
