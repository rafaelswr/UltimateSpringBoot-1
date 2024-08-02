package com.rafaelswr.SprindDataJPA.Controller;

import com.rafaelswr.SprindDataJPA.Model.School;
import com.rafaelswr.SprindDataJPA.Model.Student;
import com.rafaelswr.SprindDataJPA.Model.StudentRecordDTO;
import com.rafaelswr.SprindDataJPA.Model.StudentResponseDTO;
import com.rafaelswr.SprindDataJPA.Repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //Save student in database, through repository
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewStudent(@RequestBody StudentRecordDTO studentRecordDTO){
        var student = toStudent(studentRecordDTO);

        try{
            studentRepository.save(student);
        } catch (Exception e) {
            // Log the error
            log.error("Error saving student", e);
            // Throw a specific exception with a message and status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save student", e);
        }
    }

    private Student toStudent(StudentRecordDTO dto){
        var student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setBirthday(dto.birthday());

        var school = new School();
        school.setId(dto.schoolId());

        student.setSchool(school);
        return student;
    }

    //get student by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponseDTO getStudentById(@PathVariable Integer id){
        Optional<Student> student = studentRepository.findById(id);
        return student.map(value -> new StudentResponseDTO(value.getFirstName(),
                value.getLastName(),value.getEmail())).orElse(new StudentResponseDTO(null, null, ""));
    }

    //get students By Name
    @GetMapping("/search/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getStudentsByName(@PathVariable String name){
        return studentRepository.findAllByFirstName(name);
    }


    //get All Students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    //Delete user
    @DeleteMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Integer id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            studentRepository.deleteById(id);
            return "Student eliminated";
        }else{
            return "algo correu mal";
        }
    }

}
