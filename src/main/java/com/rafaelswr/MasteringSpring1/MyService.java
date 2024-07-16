package com.rafaelswr.MasteringSpring1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MyService {

   // @Autowired
   // @Qualifier("mySecondBean")
    private MyClass myClass;


    public MyService(@Qualifier("myThirdBean") MyClass myClass) {
        this.myClass = myClass;
    }

    @Autowired
    public void injection(MyClass myClass){
        this.myClass = myClass;
    }

    public String tellAStory(){
        return "THe bean is saying " + this.myClass.getMyVar();
    }

    public MyClass getMyClass() {
        return myClass;
    }

}
