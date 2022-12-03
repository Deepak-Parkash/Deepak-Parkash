package com.calc.deepak;

public class StandardCalc implements Calculator {

    public RevPolishCalc rpCalc = new RevPolishCalc();
    private OpStack values = new OpStack();

    @Override
    public float evaluate(String what) throws Exception {
        return Float.valueOf("" + new CalculatorParser().start(what));
    }
}
