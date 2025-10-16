package jobs;

import java.util.Date;

import models.Animal;
import models.Status;
import models.Tutor;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {

    @Override
    public void doJob() throws Exception {
        if (Tutor.count() == 0) {
            Tutor tutor = new Tutor();
            tutor.nome = "Ana Vitória";
            tutor.email = "ana@email.com";
            tutor.telefone = "99999-9999";
            tutor.cpf = "123.456.789-00";
            tutor.login = "ana";   // login padrão
            tutor.senha = "1234";  // senha padrão
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
