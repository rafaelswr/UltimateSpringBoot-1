package com.rafaelswr.SprindDataJPA.Model;


import java.time.LocalDate;

// Possible representation to handle student object,
//  in order encapsulate the main class Student
// indicated to send, on post request like forms, example
public record StudentRecordDTO(
        String firstName,
        String lastName,
        String email,
        LocalDate birthday,
        Integer schoolId

) {

}
