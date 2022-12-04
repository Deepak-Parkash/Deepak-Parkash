package com.calc.deepak;

public class OpStackCalc {

    private StackCalc numStack = new StackCalc();

    public void push(float i) {
        numStack.push(new EntryCalc(i));
    }

    public void pop(float i) throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }
        numStack.pop();

    }

    public Boolean isEmpty() {
        return numStack.size() == 0;

    }


}
