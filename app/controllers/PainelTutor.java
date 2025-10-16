package controllers;

import java.util.List;
import models.Animal;
import models.Consulta;
import models.Tutor;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class PainelTutor extends Controller {

    public static void dadosPets() {
        String emailLogado = session.get("usuarioLogado");
        Tutor tutor = Tutor.find("email = ?1", emailLogado).first();

        List<Animal> animais = Animal.find("tutor = ?1 and status = ?2", tutor, models.Status.ATIVO).fetch();
        List<Consulta> consultas = Consulta.find("select c from Consulta c join c.animais a where a.tutor = ?1 order by c.dataConsulta desc", tutor).fetch();

        render(tutor, animais, consultas);
    }
}
