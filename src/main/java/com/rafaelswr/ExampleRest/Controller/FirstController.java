package com.rafaelswr.ExampleRest.Controller;

import com.rafaelswr.ExampleRest.Model.Order;
import com.rafaelswr.ExampleRest.Model.OrderRecord;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/first")
public class FirstController {

    //  http://.../first/greetings
    @GetMapping("/greetings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String greetings(){
        return "hello from my first controller";
    }


    @PostMapping("/order")
    @ResponseStatus(HttpStatus.OK)
    public String sendPost(@RequestBody Order order) {
        return order.toString();

    }

    // http://.../first/order-record  and send OrderRecord Object
    @PostMapping("/order-record")
    @ResponseStatus(HttpStatus.OK)
    public String sendPostRecord(@RequestBody OrderRecord order) {
        return order.toString();

    }

    // http://.../first/jose
    @PostMapping("/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public String sendPostRecord(@PathVariable String userName) {
        return "Hello, "+ userName+" page!!";

    }


    // http://.../first/hello?userName=Jose&lastName=Jose
    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public String sendPostByRequestParam(
            @RequestParam("userName") String userName,
            @RequestParam("lastName") String lastName
        ) {
        return "Hello, "+ userName+" "+ lastName;

    }


}
