package models;

import javax.persistence.*;
import java.util.*;
import play.data.validation.*;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Animal extends Model {
    
    @Required(message = "O nome do animal é obrigatório")
    @MinSize(value = 2, message = "O nome deve ter pelo menos 2 caracteres")
    public String nome;

    @Required(message = "A espécie é obrigatória")
    public String especie;

    @Required(message = "A raça é obrigatória")
    public String raca;

    @Required(message = "A data de nascimento é obrigatória")
    @InPast(message = "A data de nascimento deve ser no passado")
    @Temporal(TemporalType.DATE)
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