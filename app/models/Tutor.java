package models;

import java.util.List;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity // Define que esta classe é uma entidade que será mapeada para o banco de dados
public class Tutor extends Model {

    public String nome;
    public String email;
    public String telefone;
    public String cpf;
    
    
    public String login;  
    public String senha;   
    
    @Enumerated(EnumType.STRING) 
    public Status status;

    
  }
