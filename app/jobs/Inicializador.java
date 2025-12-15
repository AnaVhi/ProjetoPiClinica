package jobs;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Calendar;
import models.Admin;
import models.Animal;
import models.Perfil;
import models.Status;
import models.Tutor;
import models.Usuario;
import play.Play;
import play.db.jpa.Blob;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {

    @Override
    public void doJob() throws Exception {
        if (Tutor.count() == 0) {
            
            // ============================================
            // CRIAR USUÁRIO ADMINISTRADOR
            // ============================================
            Usuario usuarioAdministrador = new Usuario();
            usuarioAdministrador.login = "Admin";
            usuarioAdministrador.senha = "1111";
            usuarioAdministrador.perfil = Perfil.ADMIN;
            usuarioAdministrador.save();

            Admin administrador = new Admin();
            administrador.usuario = usuarioAdministrador;
            administrador.save();

            // ============================================
            // CRIAR 6 TUTORES
            // ============================================
            
            // TUTOR 1 - Ana Vitória
            Usuario usuarioAna = new Usuario();
            usuarioAna.login = "ana";
            usuarioAna.senha = "1111";
            usuarioAna.perfil = Perfil.TUTOR;
            usuarioAna.save();

            Tutor tutorAna = new Tutor();
            tutorAna.nome = "Ana Vitória";
            tutorAna.email = "ana@email.com";
            tutorAna.telefone = "99999-9999";
            tutorAna.cpf = "123.456.789-00";
            tutorAna.status = Status.ATIVO;
            tutorAna.usuario = usuarioAna;
            tutorAna.save();

            // TUTOR 2 - Carlos Silva
            Usuario usuarioCarlos = new Usuario();
            usuarioCarlos.login = "carlos";
            usuarioCarlos.senha = "1111";
            usuarioCarlos.perfil = Perfil.TUTOR;
            usuarioCarlos.save();

            Tutor tutorCarlos = new Tutor();
            tutorCarlos.nome = "Carlos Silva";
            tutorCarlos.email = "carlos.silva@email.com";
            tutorCarlos.telefone = "98888-7777";
            tutorCarlos.cpf = "234.567.890-11";
            tutorCarlos.status = Status.ATIVO;
            tutorCarlos.usuario = usuarioCarlos;
            tutorCarlos.save();

            // TUTOR 3 - Mariana Santos
            Usuario usuarioMariana = new Usuario();
            usuarioMariana.login = "mariana";
            usuarioMariana.senha = "1111";
            usuarioMariana.perfil = Perfil.TUTOR;
            usuarioMariana.save();

            Tutor tutorMariana = new Tutor();
            tutorMariana.nome = "Mariana Santos";
            tutorMariana.email = "mariana.santos@email.com";
            tutorMariana.telefone = "97777-6666";
            tutorMariana.cpf = "345.678.901-22";
            tutorMariana.status = Status.ATIVO;
            tutorMariana.usuario = usuarioMariana;
            tutorMariana.save();

            // TUTOR 4 - Pedro Oliveira
            Usuario usuarioPedro = new Usuario();
            usuarioPedro.login = "pedro";
            usuarioPedro.senha = "1111";
            usuarioPedro.perfil = Perfil.TUTOR;
            usuarioPedro.save();

            Tutor tutorPedro = new Tutor();
            tutorPedro.nome = "Pedro Oliveira";
            tutorPedro.email = "pedro.oliveira@email.com";
            tutorPedro.telefone = "96666-5555";
            tutorPedro.cpf = "456.789.012-33";
            tutorPedro.status = Status.ATIVO;
            tutorPedro.usuario = usuarioPedro;
            tutorPedro.save();

            // TUTOR 5 - Juliana Costa
            Usuario usuarioJuliana = new Usuario();
            usuarioJuliana.login = "juliana";
            usuarioJuliana.senha = "1111";
            usuarioJuliana.perfil = Perfil.TUTOR;
            usuarioJuliana.save();

            Tutor tutorJuliana = new Tutor();
            tutorJuliana.nome = "Juliana Costa";
            tutorJuliana.email = "juliana.costa@email.com";
            tutorJuliana.telefone = "95555-4444";
            tutorJuliana.cpf = "567.890.123-44";
            tutorJuliana.status = Status.ATIVO;
            tutorJuliana.usuario = usuarioJuliana;
            tutorJuliana.save();

            // TUTOR 6 - Roberto Almeida
            Usuario usuarioRoberto = new Usuario();
            usuarioRoberto.login = "roberto";
            usuarioRoberto.senha = "1111";
            usuarioRoberto.perfil = Perfil.TUTOR;
            usuarioRoberto.save();

            Tutor tutorRoberto = new Tutor();
            tutorRoberto.nome = "Roberto Almeida";
            tutorRoberto.email = "roberto.almeida@email.com";
            tutorRoberto.telefone = "94444-3333";
            tutorRoberto.cpf = "678.901.234-55";
            tutorRoberto.status = Status.ATIVO;
            tutorRoberto.usuario = usuarioRoberto;
            tutorRoberto.save();

            // ============================================
            // CRIAR 7 ANIMAIS
            // ============================================

            // ANIMAL 1 - Rex (Cachorro - Ana)
            Animal animal1 = new Animal();
            animal1.nome = "Rex";
            animal1.especie = "Cachorro";
            animal1.raca = "Labrador";
            animal1.nascimento = criarData(2020, 3, 15);
            animal1.sexo = "MACHO";
            animal1.status = Status.ATIVO;
            animal1.tutor = tutorAna;
            animal1.save();

            // ANIMAL 2 - Mia (Gato - Ana)
            Animal animal2 = new Animal();
            animal2.nome = "Mia";
            animal2.especie = "Gato";
            animal2.raca = "Persa";
            animal2.nascimento = criarData(2021, 5, 20);
            animal2.sexo = "FEMEA";
            animal2.status = Status.ATIVO;
            animal2.tutor = tutorAna;
            animal2.save();

            // ANIMAL 3 - Thor (Cachorro - Carlos)
            Animal animal3 = new Animal();
            animal3.nome = "Thor";
            animal3.especie = "Cachorro";
            animal3.raca = "Pastor Alemão";
            animal3.nascimento = criarData(2019, 8, 10);
            animal3.sexo = "MACHO";
            animal3.status = Status.ATIVO;
            animal3.tutor = tutorCarlos;
            animal3.save();

            // ANIMAL 4 - Luna (Gato - Mariana)
            Animal animal4 = new Animal();
            animal4.nome = "Luna";
            animal4.especie = "Gato";
            animal4.raca = "Siamês";
            animal4.nascimento = criarData(2022, 1, 5);
            animal4.sexo = "FEMEA";
            animal4.status = Status.ATIVO;
            animal4.tutor = tutorMariana;
             animal4.save();

            // ANIMAL 5 - Bob (Cachorro - Pedro)
            Animal animal5 = new Animal();
            animal5.nome = "Bob";
            animal5.especie = "Cachorro";
            animal5.raca = "Bulldog";
            animal5.nascimento = criarData(2020, 11, 25);
            animal5.sexo = "MACHO";
            animal5.status = Status.ATIVO;
            animal5.tutor = tutorPedro;
            animal5.save();

            // ANIMAL 6 - Nina (Gato - Juliana)
            Animal animal6 = new Animal();
            animal6.nome = "Nina";
            animal6.especie = "Gato";
            animal6.raca = "Vira-lata";
            animal6.nascimento = criarData(2021, 7, 12);
            animal6.sexo = "FEMEA";
            animal6.status = Status.ATIVO;
            animal6.tutor = tutorJuliana;
            animal6.save();

            // ANIMAL 7 - Max (Cachorro - Roberto)
            Animal animal7 = new Animal();
            animal7.nome = "Max";
            animal7.especie = "Cachorro";
            animal7.raca = "Golden Retriever";
            animal7.nascimento = criarData(2018, 4, 8);
            animal7.sexo = "MACHO";
            animal7.status = Status.ATIVO;
            animal7.tutor = tutorRoberto;
            animal7.save();
        }
    }


    // ============================================
    // MÉTODO AUXILIAR PARA CRIAR DATAS
    // ============================================
    private Date criarData(int ano, int mes, int dia) {
        Calendar cal = Calendar.getInstance();
        cal.set(ano, mes - 1, dia); // Mês começa em 0
        return cal.getTime();
    }
}