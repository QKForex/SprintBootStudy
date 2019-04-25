package com.qkforex.springbootstudy.chapter01.iocdi;

import org.springframework.stereotype.Service;

@Service
public class FunctionService {

    public String sayHello(String word) {
        return "Hello " + word + " !";
    }
}
