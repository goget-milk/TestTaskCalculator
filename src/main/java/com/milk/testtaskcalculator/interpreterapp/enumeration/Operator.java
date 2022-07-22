package com.milk.testtaskcalculator.interpreterapp.enumeration;

import java.util.Arrays;

public enum Operator {
    PLUS('+'), MINUS('-'), MULTI('*'), DIV('/');

    Character value;

    Operator(Character value) {
        this.value = value;
    }

    Character getValue() {
        return value;
    }

    public static boolean isOperator(Character op) {
        for(Operator item: Operator.values()){
            if (op.equals(item.value)){
                return true;
            }
        }
        return false;
    }

    public static Operator fromChar(char s) throws IllegalArgumentException {
        return Arrays.stream(Operator.values())
                .filter(v -> v.value.equals(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Неизвестный оператор"));
    }
}