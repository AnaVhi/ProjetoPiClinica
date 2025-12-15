package models;

import javax.persistence.*;
import play.data.validation.*;
import play.db.jpa.Model;

@Entity
public class Tutor extends Model {

    @Required(message = "O nome é obrigatório")
    @MinSize(value = 3, message = "O nome deve ter pelo menos 3 caracteres")
    @MaxSize(value = 150, message = "O nome deve ter no máximo 150 caracteres")
    public String nome;

    @Required(message = "O e-mail é obrigatório")
    @Email(message = "Informe um e-mail válido")
    public String email;

    @Required(message = "O CPF é obrigatório")
    @Match(value = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF deve estar no formato 000.000.000-00")
    public String cpf;

    @Required(message = "O telefone é obrigatório")
    @Match(value = "^\\d{4,5}-\\d{4}$", message = "Telefone deve estar no formato 0000-0000 ou 00000-0000")
    public String telefone;

    @Enumerated(EnumType.STRING)
    public Status status;

    @OneToOne
    public Usuario usuario;

    public Tutor() {
        this.status = Status.ATIVO;
    }

    @Override
    public String toString() {
        return nome;
    }
}