package models;

import java.util.List;

import javax.persistence.*;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Tutor extends Model {
	@Required
	public String nome;
	@Required
    public String email;
	@Required
    public String telefone;
	@Required
    public String cpf;
  
	
	@OneToOne
	public Usuario usuario;
    
    @Enumerated(EnumType.STRING) 
    public Status status;

    
  }
