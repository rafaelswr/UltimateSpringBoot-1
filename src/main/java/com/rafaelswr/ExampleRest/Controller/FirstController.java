package com.rafaelswr.ExampleRest.Controller;

import com.rafaelswr.ExampleRest.Model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public String sendName(@RequestBody Order order) {
        return "Customer: "+ order.getCustomerName()+
                "\nProductName: "+order.getProductName()+"\nQuantity: "+order.getQuantity();

    }




}
