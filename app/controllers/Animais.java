package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import models.Status;
import models.Animal;
import models.Tutor;
import play.libs.MimeTypes;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import play.data.validation.Valid;
import play.db.jpa.Blob;

@With({Seguranca.class, AutorizacaoAdmin.class})
public class Animais extends Controller {

    public static void listar(String termo) {
        List<Animal> animais;
        if (termo == null || termo.isEmpty()) {
            animais = Animal.find("status = ?1", Status.ATIVO).fetch();
        } else {
            animais = Animal.find(
                "(lower(nome) like ?1 or lower(especie) like ?1) and status = ?2",
                "%" + termo.toLowerCase() + "%",
                Status.ATIVO
            ).fetch();
        }
        render(animais, termo);
    }

    public static void form() {
        List<Tutor> tutores = Tutor.find("status = ?1", Status.ATIVO).fetch();
        render(tutores);
    }

  
    public static void salvar(@Valid Animal a, File foto, String opcaoFoto) throws FileNotFoundException {
        if (validation.hasErrors()) {
            params.flash();
            validation.keep();
            form();
        }

        validation.clear();
        flash.clear();

        if (a.id == null) {
            a.status = Status.ATIVO;
        }


        a.save();
        flash.success("Animal cadastrado com sucesso!");
        detalhar(a);
    }

    public static void editar(Long id) {
        Animal a = Animal.findById(id);
        List<Tutor> tutores = Tutor.find("status = ?1", Status.ATIVO).fetch();
        renderTemplate("Animais/form.html", a, tutores);
    }

    public static void detalhar(Animal a) {
        render(a);
    }

    public static void remover(Long id) {
        Animal animal = Animal.findById(id);
        animal.status = Status.INATIVO;
        animal.save();
        listar(null);
    }

}