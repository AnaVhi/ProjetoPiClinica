package controllers;

import models.Tutor;
import play.mvc.Controller;

public class Logins extends Controller {

    public static void form() {
        render();
    }

    public static void logar(String login, String senha) {
     	Tutor tutor = Tutor.find("login = ?1 and senha = ?2",
              	login, senha).first();
     	if (tutor == null) {
          	flash.error("Login ou senha inválidos");
          	form(); 
     	} else {
          	session.put("usuarioLogado", tutor.email);
          	flash.success("Logado com sucesso!");
          	PainelTutor.dadosPets(); 
     	}
 	}
	
	public static void logout() {
		session.clear();
		flash.success("Você saiu do sistema!");
		form();
	}
 
    
   
}
