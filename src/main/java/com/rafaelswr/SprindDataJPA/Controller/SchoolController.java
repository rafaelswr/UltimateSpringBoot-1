package com.rafaelswr.SprindDataJPA.Controller;

import com.rafaelswr.SprindDataJPA.Model.School;
import com.rafaelswr.SprindDataJPA.Model.SchoolRecordDTO;
import com.rafaelswr.SprindDataJPA.Model.Student;
import com.rafaelswr.SprindDataJPA.Repository.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolRecordDTO> getAllSchools(){
        List<School> schools = schoolRepository.findAll();
        return schools
               .stream()
               .map(this::toSchoolRecordDTO)
               .toList();
    }

    private SchoolRecordDTO toSchoolRecordDTO(School school){
        return new SchoolRecordDTO(school.getName());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNewSchool(@RequestBody SchoolRecordDTO schoolDTO){
        School school = toSchoolObject(schoolDTO);
        try {
            schoolRepository.save(school);
            return ResponseEntity.ok("New School!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create school: " + e.getMessage());
        }
    }

    private School toSchoolObject(SchoolRecordDTO schoolDTO) {
        return new School(schoolDTO.name());
    }

}
