package com.milk.testtaskcalculator.interpreterapp.operation.implementation;

import com.milk.testtaskcalculator.interpreterapp.expression.Expression;
import com.milk.testtaskcalculator.interpreterapp.operation.Operation;

public class Add implements Operation {
    @Override
    public int execute(Expression left, Expression right) {
        return left.interpret() + right.interpret();
    }
}
