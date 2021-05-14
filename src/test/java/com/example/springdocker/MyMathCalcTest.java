package com.example.springdocker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Miwa Guhrés
 * Date: 2021-05-14
 * Time: 11:05
 * Project: springdocker
 * Copyright: MIT
 */
class MyMathCalcTest {

    @Test
    void add() {
        MyMathCalc sut = new MyMathCalc();
        int expected = 30;
        int actual = sut.add(15,15);
        assertEquals(expected, actual);

        assertEquals(2, sut.add(1,1));
        assertEquals(1000000, sut.add(999999,1));
        assertEquals(-6, sut.add(-10,4));

    }

    @Test
    void multiply() {
        MyMathCalc sut = new MyMathCalc();
        int expected = 0;
        int actual = sut.multiply(0,15);
        assertEquals(expected, actual);

        assertEquals(10, sut.multiply(2,5));
        assertEquals(10, sut.multiply(-2,-5));
        assertEquals(-10, sut.multiply(-2,5));
    }

    @Test
    void divide() {
        MyMathCalc sut = new MyMathCalc();
        double expected = 12.5;
        double actual = sut.divide(75,6);
        assertEquals(expected, actual);

        assertEquals(-2.77777777, sut.divide(-25,9), 0.0000001);
        assertEquals(19.8, sut.divide(99,5), 0);
        assertEquals(13.28813, sut.divide(784,59), 0.01);

    }

    @Test
    void divideByZero(){
        MyMathCalc sut = new MyMathCalc();
        assertThrows(ArithmeticException.class, ()-> sut.divide(12,0));
    }

}