package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Autorizacao extends Controller {

    @Before
    static void verificarAdmin() {
        String perfil = session.get("usuarioPerfil");
        if (perfil == null || !"ADMIN".equals(perfil)) {
            flash.error("Acesso negado! Apenas administradores podem acessar esta área.");
            Application.paginaInicialAdmin();
        }
    }

    @Before
    static void verificarTutor() {
        String perfil = session.get("usuarioPerfil");
        if (perfil == null || !"TUTOR".equals(perfil)) {
            flash.error("Acesso negado! Apenas tutores podem acessar esta área.");
            Application.paginaInicialTutor();
        }
    }
}
