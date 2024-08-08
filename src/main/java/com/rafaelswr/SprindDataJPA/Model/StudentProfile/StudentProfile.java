package com.rafaelswr.SprindDataJPA.Model.StudentProfile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rafaelswr.SprindDataJPA.Model.Student.Student;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

@Entity
@Data
@Table(name = "student_profile")
@NoArgsConstructor
@Profile({"dev", "prod"})
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bio;

    //extra field on StudentProfile table with name student_id FK
    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @JsonBackReference
    private Student student;

    public StudentProfile(String bio) {
        this.bio = bio;
    }


}
