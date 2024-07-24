package com.rafaelswr.ExampleRest.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public record OrderRecord(
        String customerName,
        String productName,
        int quantity
) {

}
