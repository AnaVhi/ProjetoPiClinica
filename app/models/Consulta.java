package models;

import play.db.jpa.Model;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Consulta extends Model {

	public LocalDate dataConsulta;

    @ManyToMany
    public List<Animal> animais;
}
