package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class AutorizacaoTutor extends Controller {

    @Before
    static void verificarTutor() {
        String perfil = session.get("usuarioPerfil");
        if (perfil == null || !"TUTOR".equals(perfil)) {
            forbidden("Acesso negado! Apenas tutores podem acessar esta área.");
        }
    }
}
