package com.calc.deepak;

public class RevPolishCalc implements CalculatorCalc {

    private NumStackCalc values = new NumStackCalc();

    @Override
    public float evaluate(String what) throws Exception {


        return Float.valueOf("" + CalculatorParser.evaluatePostfix(what));
    }
}
