package com.milk.testtaskcalculator.interpreterapp;

import com.milk.testtaskcalculator.interpreterapp.expression.Expression;

class NumberEvaluate {
    private ExpressionEvaluate evaluate;

    public void setEvaluate(ExpressionEvaluate evaluate){
        this.evaluate = evaluate;
    }

    public Expression execute(String input) {
        return evaluate.expEval(input);
    }
}
