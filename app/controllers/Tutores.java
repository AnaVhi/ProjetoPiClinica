package controllers;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import models.Animal;
import models.Consulta;
import models.Status;
import models.Tutor;
import models.Usuario;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With; 

@With({Seguranca.class, AutorizacaoAdmin.class})
public class Tutores extends Controller {

    public static void form() {
        render();
    }

    public static void listar(String termo) {
        List<Tutor> tutores;

        if (termo == null || termo.isEmpty()) {
            tutores = Tutor.find("status = ?1", Status.ATIVO).fetch();
        } else {
            tutores = Tutor.find(
                "(lower(nome) like ?1 or lower(email) like ?1) and status = ?2",
                "%" + termo.toLowerCase() + "%", Status.ATIVO
            ).fetch();
        }

        Map<Long, List<Animal>> animaisPorTutor = new HashMap<>();
        for (Tutor t : tutores) {
            List<Animal> animais = Animal.find(
                "tutor.id = ?1 AND status = ?2",
                t.id,
                Status.ATIVO
            ).fetch();
            animaisPorTutor.put(t.id, animais);
        }

        render(tutores, termo, animaisPorTutor);
    }
   
      
    public static void salvar(@Valid Tutor t) {
        if (validation.hasErrors()) {
            params.flash();
            validation.keep();
            form();
        }

        validation.clear();
        flash.clear();

        if (t.id == null) {
            t.status = Status.ATIVO;
        }

        t.save();
        flash.success("Tutor cadastrado com sucesso!");
        detalhar(t);
    }

    
    
    public static void editar(Long id) {
        Tutor tutor = Tutor.findById(id);
        renderTemplate("Tutores/form.html", tutor);
    }

    public static void remover(Long id) {
        Tutor tutor = Tutor.findById(id);
        tutor.status = Status.INATIVO;
        tutor.save();
        listar(null);
    }

    public static void detalhar(Tutor tutor) {
        List<Animal> animais = Animal.find(
            "tutor.id = ?1 AND status = ?2",
            tutor.id,
            Status.ATIVO
        ).fetch();
        render(tutor, animais);
    }
}
