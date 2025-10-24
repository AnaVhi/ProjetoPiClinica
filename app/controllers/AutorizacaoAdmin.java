package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class AutorizacaoAdmin extends Controller {

    @Before
    static void verificarAdmin() {
        String perfil = session.get("usuarioPerfil");

        if (perfil == null) {
            flash.error("Você deve logar no sistema.");
            redirect("Logins.form");
        } else if (!"ADMIN".equals(perfil)) {
            flash.error("Acesso negado! Apenas administradores podem acessar essa página.");
            redirect("Application.index"); // ou outra página pública
        }
    }
}
