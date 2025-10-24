package controllers;

import models.Usuario;
import models.Perfil;
import play.mvc.Controller;

public class Logins extends Controller {

    public static void form() {
        render();
    }

    public static void logar(String login, String senha) {
        Usuario usuario = Usuario.find("login = ?1 and senha = ?2", login, senha).first();

        if (usuario == null) {
            flash.error("Login ou senha inválidos");
            form(); // Redireciona para o form de login
        } else {
            // Guarda as informações na sessão
            session.put("usuarioLogado", usuario.login);
            session.put("usuarioPerfil", usuario.perfil.name());
            flash.success("Logado com sucesso!");

            // Redireciona conforme o perfil
            if (usuario.perfil == Perfil.ADMIN) {
                // Página inicial de administradores
                Application.paginaInicialAdmin();
            } else if (usuario.perfil == Perfil.TUTOR) {
                // Página inicial de tutores
                Application.paginaInicialTutor();
            } else {
                // Página padrão caso o perfil não seja reconhecido
                Application.index();
            }
        }
    }

    public static void logout() {
        session.clear();
        flash.success("Você saiu do sistema!");
        form();
    }
}
