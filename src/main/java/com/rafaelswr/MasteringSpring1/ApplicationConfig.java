package com.rafaelswr.MasteringSpring1;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({
        "dev","prod"
})
public class ApplicationConfig {

    @Bean("myFirstBean")
    //@Profile("dev")
    public MyClass myFirstBean(){
        return new MyClass("My First Bean");
    }
    @Bean
    @Qualifier("mySecondBean")
    public MyClass mySecondBean(){
        return new MyClass("My Second Bean");
    }

    @Bean("myThirdBean")
    //@Primary
    public MyClass myThirdBean(){
        return new MyClass("My Third Bean");
    }


}
