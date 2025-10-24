package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {

    @Before
    static void verificarAutenticacao() {
        System.out.println("Interceptador Seguranca foi chamado!");

        if (!session.contains("usuarioLogado")) {
            flash.error("Você deve logar no sistema para acessar essa página.");
            Logins.form();  // redireciona para o login
            return;         // impede que a action original continue
        }
    }
}
