package com.milk.testtaskcalculator.interpreterapp;

import com.milk.testtaskcalculator.interpreterapp.expression.Expression;

public interface ExpressionEvaluate {
    Expression expEval(String input);
}
