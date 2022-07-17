package com.milk.testtaskcalculator.interpreterapp;

import java.security.spec.ECField;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InterpreterApp {
    public static void main(String[] args) {
        //  1 + 3
        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение типа - '(10 + 30)': ");
        String str = in.nextLine();
        Context context = new Context();
        Expression expression = context.evaluate(str);
        System.out.println(str + " = " + expression.interpret());

    }
}

interface Expression {
    int interpret();
}

class NumberExpression implements Expression {
    int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }
}

enum Operand {
    PLUS("+"), MINUS("-"), MULTI("*"), DIV("/");

    String value;

    Operand(String value) {
        this.value = value;
    }

    String getValue() {
        return value;
    }

    static boolean isOperand(String str) {
        for(Operand item: Operand.values()){
            if (str.equals(item.value)){
                return true;
            }
        }
        return false;
    }
}

enum RomNum {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100),
    CD(400), D(500), CM(900), M(1000);

    int value;

    RomNum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    static List getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomNum e) -> e.value).reversed())
                .collect(Collectors.toList());
    }
}

class Util {
    static int romToArab(String str) {
        String romanNumeral = str.toUpperCase();
        int result = 0;

        List romanNumerals = RomNum.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomNum symbol = (RomNum) romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(str + " неверный символ римского числа");
        }

        return result;
    }

    static String arabToRom(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " чисто меньше 1 больше 4000 не обрабатываются");
        }

        List romanNumerals = RomNum.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomNum currentSymbol = (RomNum) romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    static boolean isPresentInRom(String ch) {
        for (RomNum val : RomNum.values()) {
            if (val.toString().equals(ch)) {
                return true;
            }
        }
        return false;
    }
}

class AcceptDiffExpressions implements  Expression{
    Expression left;
    Expression right;
    char operator;

    public AcceptDiffExpressions(Expression left, Expression right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public int interpret() {
        switch (operator) {
            case '-' -> {
                return left.interpret() - right.interpret();
            }
            case '+' -> {
               return left.interpret() + right.interpret();
            }
            case '*' -> {
                return left.interpret() * right.interpret();
            }
            case '/' -> {
                return left.interpret() / right.interpret();
            }
            default -> throw new IllegalStateException("Неверный оператор: " + operator);
        }
    }
}

class Context {
    int operatorCount = 0;

    Expression evaluate(String s){
        if (operatorCount > 1 ) {
            throw new RuntimeException("Выражение содержит больше одного оператора!");
        }
        String stringWithoutSpaces = s.strip().toUpperCase().replace(" ", "");
        int position = stringWithoutSpaces.length() - 1;
        while (position > 0) {
            if (Character.isDigit(stringWithoutSpaces.charAt(position))) {
                position--;
            } else if (!Operand.isOperand(Character.toString(stringWithoutSpaces.charAt(position)))) {
                throw new RuntimeException("Ошибка ввода");
            } else {
                operatorCount++;
                Expression left = evaluate(stringWithoutSpaces.substring(0, position));
                Expression right = new NumberExpression(Integer.parseInt(stringWithoutSpaces.substring(position+1)));
                char operator = stringWithoutSpaces.charAt(position);
                return new  AcceptDiffExpressions(left, right, operator);
            }
        }
        int result = Integer.parseInt(stringWithoutSpaces);
        return new NumberExpression(result);
    }
}













