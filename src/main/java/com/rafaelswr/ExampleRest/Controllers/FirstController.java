package com.rafaelswr.ExampleRest.Controllers;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/greetings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String greetings(){
        return "hello from my first controller";
    }

    @PostMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public String sendName(@PathVariable String name) {
        return "Hello "+ name;
    }



}
