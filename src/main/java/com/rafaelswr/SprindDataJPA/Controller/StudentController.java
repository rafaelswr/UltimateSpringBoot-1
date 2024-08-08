package com.rafaelswr.SprindDataJPA.Controller;

import com.rafaelswr.SprindDataJPA.Model.Student.StudentRecordDTO;
import com.rafaelswr.SprindDataJPA.Model.Student.StudentResponseDTO;
import com.rafaelswr.SprindDataJPA.Repository.StudentRepository;
import com.rafaelswr.SprindDataJPA.Service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentService studentService,StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;

    }

    //Save student in database, through repository
    @PostMapping("/save")
    public ResponseEntity<String> createNewStudent(@RequestBody StudentRecordDTO studentRecordDTO){
        studentService.saveNewStudentDB(studentRecordDTO);
        return new ResponseEntity<>("Student created successfully", HttpStatus.CREATED);
    }


    //get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Integer id) {
        try{
            return new ResponseEntity<>(studentService.getStudentByIdFromDB(id), HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    //get students By Name
    @GetMapping("/search/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponseDTO> getStudentsByName(@PathVariable String name){
        return studentService.searchStudentsByFirstName(name);
    }


    //get All Students
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents(){
       return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    //Delete user
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id){
        try {
            studentService.deleteStudentById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
