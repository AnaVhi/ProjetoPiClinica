package controllers;

import models.Animal;
import models.Consulta;
import play.mvc.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Consultas extends Controller {

    public static void form() {
        List<Animal> animais = Animal.find("status = ?1", models.Status.ATIVO).fetch();
        render(animais);
    }

    public static void salvar(Consulta consulta, List<Long> consulta_animais_id) {
        if (consulta_animais_id != null && !consulta_animais_id.isEmpty()) {
            consulta.animais = Animal.find("id in (:ids)").bind("ids", consulta_animais_id).fetch();
        } else {
            consulta.animais = new ArrayList<>();
        }

        consulta.save();
        flash.success("Consulta cadastrada com sucesso.");
        listar();
    }



    public static void listar() {
        List<Consulta> consultas = Consulta.findAll();
        render(consultas);
    }
}
