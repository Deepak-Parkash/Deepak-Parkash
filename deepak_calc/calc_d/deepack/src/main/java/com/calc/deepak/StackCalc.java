package com.calc.deepak;

import java.util.ArrayList;
import java.util.List;

public class StackCalc {

    private Integer size=0;
    private List<EntryCalc> entries = new ArrayList<>();

    public Integer size() {

        size = entries.size();
        return size;
    }

    public EntryCalc top() throws Exception {
        if (entries.isEmpty()) {
            throw new Exception();
        }

        return entries.get(0);
    }

    public EntryCalc pop() throws Exception {
        if (entries.isEmpty()) {
            throw new Exception();
        }
        return entries.remove(entries.size() - 1);
    }

    public void push(EntryCalc i) {
        entries.add(i);
    }


}