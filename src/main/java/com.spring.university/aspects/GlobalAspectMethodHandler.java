package com.spring.university.aspects;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.stereotype.Component;


@Aspect
@Component
public class GlobalAspectMethodHandler {

    @Before(value = "execution(* com.spring.university..*.*(..))")
    public void doSomething(){
        /* */
    }
}
