package com.milk.testtaskcalculator.interpreterapp;

import com.milk.testtaskcalculator.interpreterapp.enumeration.Operator;
import com.milk.testtaskcalculator.interpreterapp.expression.AcceptDiffExpressions;
import com.milk.testtaskcalculator.interpreterapp.expression.Expression;
import com.milk.testtaskcalculator.interpreterapp.expression.NumberExpression;
import com.milk.testtaskcalculator.interpreterapp.util.Utils;

class ArabNumberEvaluate implements ExpressionEvaluate{
    private int operatorCount = 0;
    private int operandCount = 0;

    @Override
    public Expression expEval(String input) {
        String inputWS = input.strip().replace(" ", "").toUpperCase();
        int position = inputWS.length() - 1;

        while (position > 0) {

            if (Character.isDigit(inputWS.charAt(position))) {

                if (operandCount > 2) {
                    throw new IllegalArgumentException("Слишком много цифр в числе (1-10)");
                }

                operandCount++;
                position--;

            } else if (Utils.isPresentInRom(Character.toString(inputWS.charAt(position)))) {

                throw new IllegalArgumentException("Арабские цифры не совместиы с римскими");

            } else if (!Operator.isOperator(inputWS.charAt(position))) {

                throw new IllegalArgumentException("Ошибка ввода");

            } else {

                if (operatorCount >= 1) {
                    throw new IllegalArgumentException("Слишком много операторов, используйте только один (+,-,/,*)");
                }

                operatorCount++;
                Expression left = expEval(inputWS.substring(0, position));
                Expression right = new NumberExpression(Integer.parseInt(inputWS.substring(position+1)));
                Operator operator = Operator.fromChar(inputWS.charAt(position));
                return new AcceptDiffExpressions(left, right, operator);
            }
        }
        int result = Integer.parseInt(inputWS);
        return new NumberExpression(result);
    }
}
