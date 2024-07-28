package com.rafaelswr.SprindDataJPA.Model;

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

    public StudentProfile(String bio) {
        this.bio = bio;
    }
}
