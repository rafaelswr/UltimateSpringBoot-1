package com.rafaelswr.MasteringSpring1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class MasteringSpring1Application {

	//#########SPRING BEANS##############

	public static void main(String[] args) {
		var app = new SpringApplication(MasteringSpring1Application.class);
		app.setDefaultProperties(Collections.singletonMap("spring.profiles.active","dev"));
		var context = app.run(args);


		//MyClass myClass = context.getBean("myClass", MyClass.class);
		//System.out.println(myClass.hello());

		MyService myService = context.getBean(MyService.class);
		System.out.println("JAVA -v: " + myService.getJavaVersion());
		System.out.println("My Property: " + myService.readProperty());
		System.out.println("OS name:  " + myService.getOSName());
		myService.setOSName("Linux");
		System.out.println("OS name:  " + myService.getOSName());

		System.out.println("ClassPath: " + myService.getClassPath() );
		System.out.println("Property From another file: " + myService.getMyCustomPropertyFromAnotherFile());
		System.out.println("Property from file 2: " + myService.getMyFile2Property());
		System.out.println(myService.tellAStory());


	}

}
