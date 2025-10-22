package controllers;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import models.Animal;
import models.Consulta;
import models.Status;
import models.Tutor;
import models.Usuario;
import play.mvc.Before;
import play.mvc.Controller;

public class Tutores extends Controller {

    // 1. Verificação antes de cada ação
    @Before
    static void protegerPaginas() {
        // Se o usuário não estiver logado
        if (!session.contains("usuarioLogado")) {
            flash.error("Você deve logar no sistema.");
            Logins.form();
        }

        // Identifica qual método está sendo acessado (ex: painel, listar, etc.)
        String action = request.actionMethod;

        // O método 'painel' é exclusivo de Tutor
        if ("painel".equals(action)) {
            String perfil = session.get("usuarioPerfil");
            if (!"TUTOR".equals(perfil)) {
                flash.error("Acesso negado! Apenas tutores podem acessar esta página.");
                Application.index();
            }
        } else {
            // Todos os outros métodos são apenas para Admin
            String perfil = session.get("usuarioPerfil");
            if (!"ADMIN".equals(perfil)) {
                flash.error("Acesso negado! Apenas administradores podem acessar esta página.");
                Application.index();
            }
        }
    }

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
            List<Animal> animais = Animal.find("tutor.id = ?1 AND status = ?2", t.id, Status.ATIVO).fetch();
            animaisPorTutor.put(t.id, animais);
        }

        render(tutores, termo, animaisPorTutor);
    }

    public static void salvar(Tutor tutor) {
        if (tutor.nome != null) {
            tutor.nome = tutor.nome.toUpperCase();
        }

        if (tutor.email != null) {
            tutor.email = tutor.email.toLowerCase();
        }

        if (tutor.id == null) {
            tutor.status = Status.ATIVO;
        }

        tutor.save();
        detalhar(tutor);
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
        List<Animal> animais = Animal.find("tutor.id = ?1 AND status = ?2", tutor.id, Status.ATIVO).fetch();
        render(tutor, animais);
    }
}
