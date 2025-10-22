package controllers;

import java.util.List;

import controllers.Autorizacao;
import models.Status;
import models.Animal;
import models.Tutor;
import play.mvc.Controller;

public class Animais extends Controller {
	
	public static void paginaInicial() {
        render();
    }

    // Action: lista todos os animais ativos, com busca por nome ou espécie
    public static void listar(String termo) {
        List<Animal> animais;

        if (termo == null || termo.isEmpty()) {
            // Lista todos os animais com status ATIVO
            animais = Animal.find("status = ?1", Status.ATIVO).fetch();
        } else {
            // Busca por nome ou espécie, ignorando maiúsculas/minúsculas
            animais = Animal.find(
                "(lower(nome) like ?1 or lower(especie) like ?1) and status = ?2",
                "%" + termo.toLowerCase() + "%",
                Status.ATIVO
            ).fetch();
        }

        render(animais, termo);
    }

    // Action: exibe o formulário para cadastrar ou editar um animal
    public static void form() {
        List<Tutor> tutores = Tutor.find("status = ?1", Status.ATIVO).fetch(); // Só tutores ativos
        render(tutores);
    }

    // Action: salva ou atualiza um animal
    public static void salvar(Animal a) {
       
        // Define como ATIVO se for novo
        if (a.id == null) {
            a.status = Status.ATIVO;
        }

        a.save();
        detalhar(a);
    }

 // Action: carrega dados de um animal para edição
    public static void editar(Long id) {
    	
        Animal a = Animal.findById(id); // agora a variável se chama 'a'
        List<Tutor> tutores = Tutor.find("status = ?1", Status.ATIVO).fetch();
        renderTemplate("Animais/form.html", a, tutores); // envia como 'a' para o form
    }


    // Action: exibe os detalhes de um animal
    public static void detalhar(Animal a) {
        render(a);
    }

    // Action: remove logicamente (altera o status para INATIVO)
    public static void remover(Long id) {
        Animal animal = Animal.findById(id);
        animal.status = Status.INATIVO;
        animal.save();
        listar(null);
    }
}
