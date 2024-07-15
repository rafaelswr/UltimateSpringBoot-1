package com.rafaelswr.MasteringSpring1;

import org.springframework.stereotype.Component;


public class MyClass {

    private String myVar;

    public MyClass(String myVar) {
        this.myVar = myVar;
    }

    public String getMyVar() {
        return myVar;
    }

    public String hello(){
        return "Ola";
    }



}
