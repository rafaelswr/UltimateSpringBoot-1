package com.rafaelswr.SprindDataJPA.Service;

import com.rafaelswr.SprindDataJPA.Model.Student.Student;
import com.rafaelswr.SprindDataJPA.Model.Student.StudentRecordDTO;
import com.rafaelswr.SprindDataJPA.Model.Student.StudentResponseDTO;
import com.rafaelswr.SprindDataJPA.Repository.StudentRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;
/*
    @BeforeAll
    static void beforeAll() {
        var student1 = new StudentRecordDTO("Rafael", "Borges", "y****@gmail.com", LocalDate.of(1999,2,13),2);
        var student2 = new StudentRecordDTO("Mário", "Lima", "lima@gmail.com", LocalDate.of(1979,8,24),3);
        var student3 = new StudentRecordDTO("Filipe", "Represa", "represa@gmail.com", LocalDate.of(1981,11,15),1);

}
 */
    //AfterAll
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentService = new StudentService(studentRepository);
    }

    @Test
    @DisplayName("StudentDTO->Student test ")
    public void shouldMapStudentDTOtoStudent(){
        var student3DTO = new StudentRecordDTO(null, "represa@gmail.com","", LocalDate.of(1981,11,15),1);
        Student s = studentService.toStudent(student3DTO);
        assertEquals(student3DTO.firstName(), s.getFirstName());
        assertEquals(student3DTO.lastName(), s.getLastName());
        assertEquals(student3DTO.email(), s.getEmail());
        assertEquals(student3DTO.birthday(), s.getBirthday());
        assertEquals(student3DTO.schoolId(), s.getSchool().getId());

        assertNotNull(s.getSchool());

    }

    @Test
    public void shouldSaveNewInstance(){
        Student student = Student.builder()
                .firstName("Sam")
                .lastName("Curran")
                .email("sam@gmail.com")
                .build();

        studentRepository.save(student);
        assertNotNull(studentRepository.findByEmail("sam@gmail.com"));
    }


}