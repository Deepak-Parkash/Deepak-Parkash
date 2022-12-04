package com.calc.deepak;

public class CalcModel {


    CalculatorCalc revPolish;
    CalculatorCalc standard;

    public float evaluate(String expr, Boolean infix) throws Exception {
        standard = new StandardCalc();
        revPolish = new RevPolishCalc();
        float a = 0;

            if (infix) {
                a = standard.evaluate(expr);
            } else {
                a = revPolish.evaluate(expr);
            }


        return a;
    }

}
