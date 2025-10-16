package jobs;

import java.util.Date;

import models.Admin;
import models.Animal;
import models.Perfil;
import models.Status;
import models.Tutor;
import models.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {

    @Override
    public void doJob() throws Exception {
        if (Tutor.count() == 0) {
        	
        	Usuario usuarioAdministrador = new Usuario();
        	usuarioAdministrador.login = "Admin";
        	usuarioAdministrador.senha = "1111";
        	usuarioAdministrador.perfil = Perfil.ADMIN;
        	usuarioAdministrador.save();
			
			Admin administrador = new Admin();
			administrador.usuario = usuarioAdministrador;
			administrador.save();
      	
			
			Usuario usuarioAna = new Usuario();
			usuarioAna.login = "ana";
			usuarioAna.senha = "1111";
			usuarioAna.save();
        	
            Tutor tutor = new Tutor();
            tutor.nome = "Ana Vitória";
            tutor.email = "ana@email.com";
            tutor.telefone = "99999-9999";
            tutor.cpf = "123.456.789-00";
            tutor.status = Status.ATIVO;
            tutor.save();

            Animal animal = new Animal();
            animal.nome = "Rex";
            animal.especie = "Cachorro";
            animal.raca = "Labrador";
            animal.nascimento = new Date();
            animal.sexo = "Macho";
            animal.status = Status.ATIVO;
            animal.tutor = tutor;
            animal.save();
        }
    }
}
