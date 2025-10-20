package controllers;

import models.Tutor;
import models.Admin;
import models.Animal;
import models.Consulta;
import models.Perfil;
import models.Usuario;
import play.mvc.Controller;

public class Logins extends Controller {
	
	public static void form() {
		render();
	}
	
	public static void logar(String login, String senha) {
	    Usuario usuario = Usuario.find("login = ?1 and senha = ?2", login, senha).first();

	    if (usuario == null) {
	        flash.error("Login ou senha inválidos");
	        form();
	    } else {
	        session.put("usuarioLogado", usuario.login);
	        session.put("usuarioPerfil", usuario.perfil.name());

	        if (usuario.perfil == Perfil.ADMIN) {
	            // Redireciona para página inicial administrativa
	            flash.success("Bem-vindo, Administrador!");
	            Application.index();
	        } else if (usuario.perfil == Perfil.TUTOR) {
	            // Busca o tutor vinculado ao usuário logado
	            Tutor tutor = Tutor.find("usuario = ?1", usuario).first();

	            if (tutor == null) {
	                flash.error("Nenhum tutor vinculado a este usuário.");
	                form();
	            } else {
	                // Busca animais e consultas do tutor
	                List<Animal> animais = Animal.find("tutor = ?1 and status = ?2", tutor, Status.ATIVO).fetch();
	                List<Consulta> consultas = Consulta.find("select c from Consulta c join c.animais a where a.tutor = ?1 order by c.dataConsulta desc", tutor).fetch();

	                // Redireciona para página personalizada
	                renderTemplate("Tutores/painel.html", tutor, animais, consultas);
	            }
	        }
	    }
	}

	
	public static void logout() {
		session.clear();
		flash.success("Você saiu do sistema!");
		form();
	}

}
