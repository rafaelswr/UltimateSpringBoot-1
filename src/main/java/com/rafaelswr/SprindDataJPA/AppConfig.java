package com.rafaelswr.SprindDataJPA;

import com.rafaelswr.SprindDataJPA.Model.School.School;
import com.rafaelswr.SprindDataJPA.Model.School.SchoolRecordDTO;
import com.rafaelswr.SprindDataJPA.Model.Student.Student;
import com.rafaelswr.SprindDataJPA.Model.Student.StudentRecordDTO;
import com.rafaelswr.SprindDataJPA.Repository.SchoolRepository;
import com.rafaelswr.SprindDataJPA.Repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class AppConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            SchoolRepository schoolRepository,
            StudentRepository studentRepository
    ){
        return args -> {
            var estig = new School("ESTiG");
            var eseb = new School("ESEB");
            var esab = new School("ESAB");

            var rafa = new Student("Rafael", "Borges", "r****@gmail.com",LocalDate.of(1999,1,13));
            var ana = new Student("Ana", "Garcia", "a*****@gmail.com",LocalDate.of(2000,5,23));
            var pedro = new Student("Pedro", "Alves", "p*****@gmail.com",LocalDate.of(2002,4,9));

            rafa.setSchool(estig);
            pedro.setSchool(eseb);
            ana.setSchool(esab);

            schoolRepository.save(esab);
            schoolRepository.save(estig);
            schoolRepository.save(eseb);

            studentRepository.save(pedro);
            studentRepository.save(rafa);
            studentRepository.save(ana);


        };
    }


}
