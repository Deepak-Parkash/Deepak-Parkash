package com.calc.deepak;

public class StrStackCalc {
    private StackCalc strStack=new StackCalc();

    public String pop() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }
        return strStack.pop().getString();
    }

    public void push(String str) {
        if (str.isEmpty()) {
            strStack.push(new EntryCalc(SymbolCalc.INVALID));
        } else if (str.equals("+")) {
            strStack.push(new EntryCalc(SymbolCalc.PLUS));
        } else if (str.equals("-")) {
            strStack.push(new EntryCalc(SymbolCalc.MINUS));
        } else if (str.equals("/")) {
            strStack.push(new EntryCalc(SymbolCalc.DIVIDE));
        } else if (str.equals("*")) {
            strStack.push(new EntryCalc(SymbolCalc.TIMES));
        } else if (str.equals("]")) {
            strStack.push(new EntryCalc(SymbolCalc.RIGHT_BRACKET));
        } else if (str.equals("[")) {
            strStack.push(new EntryCalc(SymbolCalc.LEFT_BRACKET));
        }
    }

    public Boolean isEmpty() {
        if (strStack.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
