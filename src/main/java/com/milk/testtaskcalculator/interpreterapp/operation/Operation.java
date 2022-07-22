package com.milk.testtaskcalculator.interpreterapp.operation;
import com.milk.testtaskcalculator.interpreterapp.expression.Expression;

public interface Operation {
    int execute(Expression left, Expression right);
}
