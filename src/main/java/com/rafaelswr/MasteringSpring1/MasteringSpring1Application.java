package com.rafaelswr.MasteringSpring1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MasteringSpring1Application {


	public static void main(String[] args) {
		var context = SpringApplication.run(MasteringSpring1Application.class, args);

		MyClass myClass = context.getBean("myClass", MyClass.class);
		System.out.println(myClass.hello());
	}


}
