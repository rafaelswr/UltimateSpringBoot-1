package com.rafaelswr.SprindDataJPA.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

@Entity
@Profile({"dev", "prod"})
@Table(name = "school")
@Data
@NoArgsConstructor
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public School(String name) {
        this.name = name;
    }
}
