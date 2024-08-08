package com.rafaelswr.SprindDataJPA.Model.Student;

//expose response student object
public record StudentResponseDTO(
    String firstName,
    String lastName,
    String email
) {

}
