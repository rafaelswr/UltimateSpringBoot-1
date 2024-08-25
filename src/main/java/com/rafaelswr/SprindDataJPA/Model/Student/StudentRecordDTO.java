package com.rafaelswr.SprindDataJPA.Model.Student;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

// Possible representation to handle student object,
//  in order encapsulate the main class Student
// indicated to send, on post request like forms, example

public record StudentRecordDTO(
        @NotEmpty(message = "FirstName should not be empty")
        String firstName,

        @NotEmpty(message = "LastName should not be empty")
        String lastName,

        @Email(message = "Email should be valid!")
        String email,

        @Past
        LocalDate birthday,
        Integer schoolId

) {

}
