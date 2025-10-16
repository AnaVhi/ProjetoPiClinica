package models;

import java.util.Date;
import javax.persistence.*;

import play.db.jpa.Model;

@Entity // Indica que esta é uma entidade que será mapeada no banco de dados
public class Animal extends Model {

    // Nome do animal
    public String nome;

    // Espécie do animal (ex: cachorro, gato)
    public String especie;

    // Raça do animal
    public String raca;

    // Data de nascimento do animal
    @Temporal(TemporalType.DATE) // Define que a data será salva no formato apenas de data (sem hora)
    public Date nascimento;

    // Sexo do animal (ex: macho, fêmea)
    public String sexo;

    // Status do animal (ATIVO ou INATIVO)
    @Enumerated(EnumType.STRING) // Diz ao JPA para salvar o texto (ATIVO/INATIVO) no banco
    public Status status;

    // Relacionamento: Muitos animais pertencem a um único tutor
    @ManyToOne
    public Tutor tutor; // Apenas um tutor por animal
}
