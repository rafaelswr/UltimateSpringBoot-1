package com.rafaelswr.SprindDataJPA.Model;

//expose response student object
public record StudentResponseDTO(
    String firstName,
    String lastName,
    String email
) {

}
