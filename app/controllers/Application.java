package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.Animal;
import models.Status;
import models.Tutor;
import models.Usuario;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void paginaInicialAdmin() {
        AutorizacaoAdmin.verificarAdmin();
        render();
    }

    public static void paginaInicialTutor() {
        String login = session.get("usuarioLogado");
        Usuario usuario = Usuario.find("login = ?1", login).first();
        Tutor tutor = Tutor.find("usuario = ?1", usuario).first();

        if (tutor == null) {
            flash.error("Não foi possível carregar seus dados de tutor.");
            Logins.form();
        }

        List<Animal> animais = Animal.find(
            "tutor = ?1 AND status = ?2",
            tutor,
            Status.ATIVO
        ).fetch();

        render(tutor, animais);
    }
}
