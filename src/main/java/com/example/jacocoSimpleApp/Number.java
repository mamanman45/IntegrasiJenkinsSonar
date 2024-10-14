package com.example.jacocoSimpleApp;

import org.springframework.stereotype.Component;

@Component
public class Number {
    public boolean isEven(int number) {
        return number % 2 == 0;
    }
}
