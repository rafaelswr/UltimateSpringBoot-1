package com.rafaelswr.MasteringSpring1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MyService {

   // @Autowired
   // @Qualifier("mySecondBean")
    private MyClass myClass;
    private  String javaVersion;

    @Autowired
    private Environment environment;

    public MyService(@Qualifier("myThirdBean") MyClass myClass) {
        this.myClass = myClass;
    }

    @Autowired
    public void injection(MyClass myClass){
        this.myClass = myClass;
    }


    public String getOS(){
        return this.environment.getProperty("os.name");
    }

    public String readProperty(){
        return this.environment.getProperty("my.custom.property");
    }

    public String tellAStory(){
        return "THe bean is saying " + this.myClass.getMyVar();
    }


    @Value("#{systemProperties['java.vm.specification.version']}")
    public void setJavaVersion(String javaVersion){
        this.javaVersion = javaVersion;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public MyClass getMyClass() {
        return myClass;
    }

}
