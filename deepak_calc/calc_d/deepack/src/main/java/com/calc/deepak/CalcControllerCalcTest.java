package com.calc.deepak;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcControllerCalcTest {
    CalcController calcController;

    @BeforeEach
    void setUp() {
        calcController = new CalcController();
    }

    @AfterEach
    void tearDown() {
        calcController = null;
    }

    @Test
    void IsNotNull() {
        Assertions.assertNotNull(calcController);
    }

    @Test
    void calculateTest() {
        float a = CalculatorParser.evaluatePostfix("22+");
        Assertions.assertEquals(a, 4.0);
    }

    @Test
    void calculateTestTwo() {
        float a = Float.valueOf("" + new CalculatorParser().start("2+2"));
        Assertions.assertEquals(a, 4.0);
    }

    @Test
    void expressionType() {
        Assertions.assertTrue(new CalcController().expressionType());
    }
}