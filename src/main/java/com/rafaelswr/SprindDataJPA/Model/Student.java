package com.rafaelswr.SprindDataJPA.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.service.spi.InjectService;
import org.springframework.context.annotation.Profile;

@Entity
@NoArgsConstructor
@Profile({"dev", "prod"})
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "firstName", length = 20)
    private String firstName;
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    private int age;

    //primary Entity in relation to Student Profile
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private StudentProfile studentProfile;


    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    @JsonBackReference
    private School school;

    @Transient
    @Column(insertable = true, updatable = false)
    private String someColumn;

    public Student(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
