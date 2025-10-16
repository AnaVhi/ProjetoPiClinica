package controllers;
import java.util.Map;
import java.util.HashMap;

import java.util.List;

import models.Animal;
import models.Consulta;
import models.Status;
import models.Tutor;
import play.mvc.Controller;

public class Tutores extends Controller {
	
	//public static void dadosPets() {
        //String emailLogado = session.get("usuarioLogado");
       // Tutor tutor = Tutor.find("email = ?1", emailLogado).first();

      //  List<Animal> animais = Animal.find("tutor = ?1 and status = ?2", tutor, models.Status.ATIVO).fetch();
      //  List<Consulta> consultas = Consulta.find("select c from Consulta c join c.animais a where a.tutor = ?1 order by c.dataConsulta desc", tutor).fetch();

      //  render(tutor, animais, consultas);
   // }

	
	
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
                "%" + termo.toLowerCase() + "%",
                Status.ATIVO
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
        listar(null); // Atualiza a listagem
    }

    public static void detalhar(Tutor tutor) {
        List<Animal> animais = Animal.find("tutor.id = ?1 AND status = ?2", tutor.id, Status.ATIVO).fetch();

        render(tutor, animais);
    }

}
