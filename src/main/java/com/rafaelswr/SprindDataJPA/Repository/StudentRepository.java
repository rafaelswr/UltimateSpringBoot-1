package com.rafaelswr.SprindDataJPA.Repository;

import com.rafaelswr.SprindDataJPA.Model.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByFirstName(String name);

    Optional<Student> findByEmail(String email);

}
