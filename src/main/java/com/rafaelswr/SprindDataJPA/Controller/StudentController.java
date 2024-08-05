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
import java.util.stream.Collectors;

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
    public ResponseEntity<String> saveNewStudent(@RequestBody StudentRecordDTO studentRecordDTO){
        var student = toStudent(studentRecordDTO);
        try{
            studentRepository.save(student);
            log.info("Student created successfully");
        } catch (Exception e) {
            log.error("Error saving student", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save student", e);
        }
        return new ResponseEntity<>("Student created successfully", HttpStatus.CREATED);
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
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Integer id) {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Student ID not found !");
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Student ID not found");
                });
        return new ResponseEntity<>(toStudentResponse(student), HttpStatus.OK);
    }

    private StudentResponseDTO toStudentResponse(Student student) {
        return new StudentResponseDTO(student.getFirstName(), student.getLastName(), student
                .getEmail());
    }

    //get students By Name
    @GetMapping("/search/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponseDTO> getStudentsByName(@PathVariable String name){
        List<Student> studentsList =  studentRepository.findAllByFirstName(name);
        return studentsList.stream().map(student ->
                new StudentResponseDTO(student.getFirstName(), student.getLastName(), student.getEmail())).toList();
    }


    //get All Students
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents(){
       var students = studentRepository.findAll();
       var studentsDTO =  students.stream().map(student ->
               new StudentResponseDTO(student.getFirstName(), student.getLastName(), student.getEmail())).toList();
       return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    //Delete user
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            try{
                studentRepository.deleteById(id);
                log.info("Student has been deleted.");
                return  new ResponseEntity<>("Student has been deleted.", HttpStatus.OK);
            }catch (Error e){
                log.error("Unable to delete Student" + e.getMessage());
                throw new  ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete student", e);
            }
        }else{
            log.warn("Student with id " + id + " not found.");
            return new ResponseEntity<>("Student with id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

}
