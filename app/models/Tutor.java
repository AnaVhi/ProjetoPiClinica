package models;

import javax.persistence.*;
import play.data.validation.*;
import play.db.jpa.Model;
import play.data.validation.Email;
import play.data.validation.MinSize;
import play.data.validation.Required;

@Entity
public class Tutor extends Model {

    // ---------- CAMPOS ----------
    @Required(message = "O nome é obrigatório")
    @MinSize(value = 3, message = "O nome deve ter pelo menos 3 caracteres")
    public String nome;

    @Required(message = "O e-mail é obrigatório")
    @Email(message = "Informe um e-mail válido")
    public String email;

    @Required(message = "O CPF é obrigatório")
    public String cpf;
    
    @Required(message = "O telefone é obrigatório")
    public String telefone;

    @Enumerated(EnumType.STRING)
    public Status status;

    @OneToOne
    public Usuario usuario; // Cada tutor possui um usuário vinculado

    // ---------- MÉTODOS AUXILIARES ----------
    @Override
    public String toString() {
        return nome;
    }
}
