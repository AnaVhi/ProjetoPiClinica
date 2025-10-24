package models;

import javax.persistence.*;
import play.data.validation.*;
import play.db.jpa.Model;

@Entity
public class Usuario extends Model {

    @Required(message = "O login é obrigatório")
    @MinSize(value = 3, message = "O login deve ter ao menos 3 caracteres")
    public String login;

    @Required(message = "A senha é obrigatória")
    @MinSize(value = 4, message = "A senha deve ter ao menos 4 caracteres")
    public String senha;

    @Enumerated(EnumType.STRING)
    public Perfil perfil; 
}
