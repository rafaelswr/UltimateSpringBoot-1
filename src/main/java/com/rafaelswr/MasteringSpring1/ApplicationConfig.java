package com.rafaelswr.MasteringSpring1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfig {

    @Bean("myClass")
    public MyClass myClass(){
        return new MyClass("My First Bean");
    }


}
