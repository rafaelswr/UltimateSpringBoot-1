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
