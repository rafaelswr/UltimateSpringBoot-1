package com.rafaelswr.SprindDataJPA.Controller;

import com.rafaelswr.SprindDataJPA.Model.School;
import com.rafaelswr.SprindDataJPA.Repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<School> getAllSchools(){
        return schoolRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNewSchool(@RequestBody School school){
        try {
            schoolRepository.save(school);
            return ResponseEntity.ok("New School!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create school: " + e.getMessage());
        }
    }

}
