package com.rafaelswr.SprindDataJPA.Controller;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.rafaelswr.SprindDataJPA.Model.Student;
import com.rafaelswr.SprindDataJPA.Model.StudentRecord;
import com.rafaelswr.SprindDataJPA.Repository.StudentRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class controllerSpringData {

    private final StudentRepository studentRepository;

    @Autowired
    public controllerSpringData(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //Save student in database, through repository
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveNewStudent(@RequestBody Student student){
        studentRepository.save(student);
        return "New Student Added! ";
    }

    //get student by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentRecord getStudentById(@PathVariable Integer id){
        Optional<Student> student = studentRepository.findById(id);
        return student.map(value -> new StudentRecord(value.getFirstName(),
                value.getLastName(), value.getAge())).orElse(new StudentRecord(null, null, 0));
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
