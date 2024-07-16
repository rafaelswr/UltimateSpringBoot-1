package com.rafaelswr.MasteringSpring1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class MasteringSpring1Application {


	public static void main(String[] args) {
		var context = SpringApplication.run(MasteringSpring1Application.class, args);

		//MyClass myClass = context.getBean("myClass", MyClass.class);
		//System.out.println(myClass.hello());

		MyService myService = context.getBean(MyService.class);
		//System.out.println("Profiles: " + Arrays.toString(myService.getEnvironment().getActiveProfiles()));
		System.out.println("JAVA -v: " + myService.getOS());
		System.out.println("My Property: " + myService.readProperty());

		System.out.println(myService.tellAStory());


	}


}
