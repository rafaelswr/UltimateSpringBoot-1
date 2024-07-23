package com.rafaelswr.MasteringSpring1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySources({
        @PropertySource("classpath:custom.properties"),
        @PropertySource("classpath:file2.properties")
})
public class MyService {

   // @Autowired
   // @Qualifier("mySecondBean")
    private MyClass myClass;
    private String javaVersion;
    private String osName;
    private String classPath;
    private final Environment environment;
    private final String myCustomPropertyFromAnotherFile;

    private String myFile2Property;

    @Autowired
    public MyService(@Qualifier("mySecondBean") MyClass myClass,
                     Environment environment,
                     @Value("#{systemProperties['os.name']}") String osName,
                     @Value("${my.prop}") String myCustomPropertyFromAnotherFile,
                     @Value("${my.file.name}") String myFile2Property) {
        this.myClass = myClass;
        this.environment = environment;
        this.osName = osName;
        this.myFile2Property = myFile2Property;
        this.classPath  = System.getProperty("java.class.path");
        this.myCustomPropertyFromAnotherFile = myCustomPropertyFromAnotherFile;
        this.myFile2Property = myFile2Property;

    }

    public String getMyCustomPropertyFromAnotherFile() {
        return myCustomPropertyFromAnotherFile;
    }

    @Autowired
    public void injection(@Qualifier("mySecondBean") MyClass myClass){
        this.myClass = myClass;
    }

    public Environment getEnvironment() {
        return environment;
    }


    public String tellAStory(){
        return "THe bean is saying " + this.myClass.getMyVar();
    }


    public String readProperty(){
        return this.environment.getProperty("my.custom.property");
    }


    public void setOSName (String name){
        this.osName = name;
    }

    public String getOSName(){
       // return this.environment.getProperty("os.name");
        return osName;
    }

    public String getClassPath(){
        return this.classPath;
    }
    @Value("#{systemProperties['java.vm.specification.version']}")
    public void setJavaVersion(String javaVersion){
        this.javaVersion = javaVersion;
    }

    public String getJavaVersion() {
        return this.javaVersion;
    }

    public String getMyFile2Property() {
        return myFile2Property;
    }
}
