package com.calc.deepak;

public class AsciiViewCalc implements ViewInterfaceCalc {


    private String expression = "";
    private String answer = "";

    public void menu() {
        System.out.println("Expression : " + expression);
        System.out.println("Answer : " + answer);
    }

    @Override
    public String getExpression() {
        return expression;
    }

    @Override
    public void setAnswer(String str) {
        answer = str;
    }

    @Override
    public void addCalcObserver(Runnable ali) {

    }

    @Override
    public void addTypeObserver() {

    }
}