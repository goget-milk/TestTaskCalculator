package com.milk.testtaskcalculator.interpreterapp.expression;

import com.milk.testtaskcalculator.interpreterapp.enumeration.Operator;
import com.milk.testtaskcalculator.interpreterapp.operation.Operation;
import com.milk.testtaskcalculator.interpreterapp.operation.implementation.Add;
import com.milk.testtaskcalculator.interpreterapp.operation.implementation.Divide;
import com.milk.testtaskcalculator.interpreterapp.operation.implementation.Multiply;
import com.milk.testtaskcalculator.interpreterapp.operation.implementation.Sub;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class AcceptDiffExpressions implements Expression {
    Expression left;
    Expression right;
    Operator operator;

    public AcceptDiffExpressions(Expression left, Expression right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public int interpret() {

        Map<Operator, Operation> operationMap = new HashMap<>();
        operationMap.put(Operator.MINUS, new Sub());
        operationMap.put(Operator.PLUS, new Add());
        operationMap.put(Operator.MULTI, new Multiply());
        operationMap.put(Operator.DIV, new Divide());

        Operation operation = Optional.ofNullable(operationMap.get(operator))
                .orElseThrow(() -> new IllegalStateException("Неверный оператор"));
        return operation.execute(left, right);

    }
}

