package com.rafaelswr.SprindDataJPA.Repository;

import com.rafaelswr.SprindDataJPA.Model.School.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface SchoolRepository extends JpaRepository<School, Integer> {
}
