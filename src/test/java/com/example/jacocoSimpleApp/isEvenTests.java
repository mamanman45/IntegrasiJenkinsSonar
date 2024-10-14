package com.example.jacocoSimpleApp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JacocoSimpleAppApplication.class)
class isEvenTests {
    @Autowired
    private Number number;

    @Test
    void isNumberEvenTests() {
        assertTrue(number.isEven(2));

        assertTrue(number.isEven(3));
    }
}
