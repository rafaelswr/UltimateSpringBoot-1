package com.rafaelswr.SprindDataJPA.Repository;

import com.rafaelswr.SprindDataJPA.Model.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByFirstName(String name);

}
