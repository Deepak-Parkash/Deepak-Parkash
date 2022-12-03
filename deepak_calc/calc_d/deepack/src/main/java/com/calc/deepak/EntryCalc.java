package com.calc.deepak;

public class EntryCalc {

    private float number=0;
    private Symbol other=Symbol.INVALID;
    private String str="";
    private TypeCalc type= TypeCalc.INVALID;

    public EntryCalc(float value) {
        this.number = value;
    }

    public EntryCalc(Symbol which) {
        other = which;
    }

    public TypeCalc getType() {
        return type;
    }


    public Symbol getSymbol() throws Exception {
        return other;
    }

    public String getString() throws Exception {
        return str;
    }

    public float getValue() throws Exception {
        return number;
    }

}
