package models;

import javax.persistence.*;
import java.util.*;
import play.data.validation.*;
import play.db.jpa.Model;
import play.data.validation.Required;

@Entity
public class Animal extends Model {

    @Required(message = "O nome do animal é obrigatório")
    public String nome;

    @Required(message = "A espécie é obrigatória")
    public String especie;
    
    @Required(message = "A raça é obrigatória")
    public String raca;

    @Required(message = "A data de nascimento é obrigatória")
    @InPast(message = "A data de nascimento deve ser no passado")
    public Date nascimento;

    @Required(message = "O sexo é obrigatório")
    public String sexo;

    @ManyToOne
    @Required(message = "O tutor é obrigatório")
    public Tutor tutor;

    @Enumerated(EnumType.STRING)
    public Status status;

    @Override
    public String toString() {
        return nome;
    }
}
