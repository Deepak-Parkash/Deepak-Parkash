package com.calc.deepak;

interface ViewInterfaceCalc {
    String getExpression();

    void setAnswer(String str);

    void addCalcObserver(Runnable ali);

    void addTypeObserver();
}
