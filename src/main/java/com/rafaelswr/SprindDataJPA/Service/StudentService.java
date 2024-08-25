package com.rafaelswr.SprindDataJPA.Service;

import com.rafaelswr.SprindDataJPA.Model.School.School;
import com.rafaelswr.SprindDataJPA.Model.Student.Student;
import com.rafaelswr.SprindDataJPA.Model.Student.StudentRecordDTO;
import com.rafaelswr.SprindDataJPA.Model.Student.StudentResponseDTO;
import com.rafaelswr.SprindDataJPA.Repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveNewStudentDB(StudentRecordDTO student){
        Optional<Student> studentExists = studentRepository.findByEmail(toStudent(student).getEmail());
        if(studentExists.isEmpty()){
            studentRepository.save(studentExists.get());
            log.info("Student created successfully");
        } else {
            log.error("Error saving, student is already there!");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving, student is already there!");
        }
    }

    public StudentResponseDTO getStudentByIdFromDB(Integer id) {
        Student s =  studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Not Found student by his ID: {}", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Student ID not found");
                });
        return toStudentResponse(s);
    }

    public List<StudentResponseDTO> getAllStudents(){
        return studentRepository.findAll().stream().map(this::toStudentResponse).toList();
    }

    public List<StudentResponseDTO> searchStudentsByFirstName(String name){
        return studentRepository.findAllByFirstName(name).stream().map(this::toStudentResponse).toList();
    }

    public void deleteStudentById(Integer id){
       Optional<Student> student = studentRepository.findById(id);
       if(student.isPresent()){
           studentRepository.deleteById(id);
           log.info("Student \"{}\" has been successfully deleted!", student.get().getFirstName());
       }else {
           log.warn("Student {} not found", id);
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found!");
       }

    }

    public Student toStudent(StudentRecordDTO dto){
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

    public StudentResponseDTO toStudentResponse(Student student) {
        return new StudentResponseDTO(student.getFirstName(), student.getLastName(), student
                .getEmail());
    }



}
