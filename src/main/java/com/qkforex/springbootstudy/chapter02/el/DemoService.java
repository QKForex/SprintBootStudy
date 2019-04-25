package com.qkforex.springbootstudy.chapter02.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    @Value("其他类的属性")
    private String annother;

    public String getAnnother() {
        return annother;
    }

    public void setAnnother(String annother) {
        this.annother = annother;
    }

}
