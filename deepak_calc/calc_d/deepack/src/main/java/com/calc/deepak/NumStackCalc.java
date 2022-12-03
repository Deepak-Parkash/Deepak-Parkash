package com.calc.deepak;

public class NumStackCalc {

    private StackCalc numStack = new StackCalc();

    public void push(float i) {
        numStack.push(new EntryCalc(i));
    }

    public float pop() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }
        return numStack.pop().getValue();
    }

    public Boolean isEmpty() {
        if (numStack.size() == 0) {
            return true;
        } else {

            return false;
        }
    }
}
