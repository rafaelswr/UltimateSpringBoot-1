package com.rafaelswr.SprindDataJPA.Controller;

import com.rafaelswr.SprindDataJPA.Model.School;
import com.rafaelswr.SprindDataJPA.Model.SchoolRecordDTO;
import com.rafaelswr.SprindDataJPA.Model.Student;
import com.rafaelswr.SprindDataJPA.Repository.SchoolRepository;
import com.rafaelswr.SprindDataJPA.Service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolRecordDTO> getAllSchools(){
        return schoolService.getAllSchools();
    }


    @PostMapping("/create")
    public ResponseEntity<String> createNewSchool(@RequestBody SchoolRecordDTO schoolDTO) {
        return schoolService.saveNewSchool(schoolDTO);
    }


}
