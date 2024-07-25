package com.rafaelswr.SprindDataJPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile({
        "dev","prod"
})
public class SpringDataApplication {

    public static void main(String[] args) {
        var app = new SpringApplication(SpringDataApplication.class);
        var context = app.run(args);

    }

}
