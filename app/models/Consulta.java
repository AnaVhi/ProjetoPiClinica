package models;

import play.data.validation.InFuture;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Consulta extends Model {
	
	@Required(message = "A data da consulta é obrigatória")
    @InFuture(message = "A data da consulta deve ser uma data futura")
	public LocalDate dataConsulta;
	
	@Required(message = "O animal é obrigatório")
    @ManyToMany
    public List<Animal> animais;
}
