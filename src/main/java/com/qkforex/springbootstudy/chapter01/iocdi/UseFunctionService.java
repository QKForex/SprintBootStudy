package com.qkforex.springbootstudy.chapter01.iocdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UseFunctionService {
    @Autowired
    FunctionService functionService;

    public String sayHello(String word) {
        return functionService.sayHello(word);
    }
}
