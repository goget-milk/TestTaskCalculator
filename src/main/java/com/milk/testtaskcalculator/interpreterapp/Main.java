package com.milk.testtaskcalculator.interpreterapp;

import com.milk.testtaskcalculator.interpreterapp.util.Utils;

import java.util.Scanner;

public class Main {
    public static String calc(String input) {

        NumberEvaluate numberEvaluate = new NumberEvaluate();
        String inputWS = input.strip().replace(" ", "").toUpperCase();
        if (inputWS.equals("")){
            throw new IllegalArgumentException("Пустая строка!");
        } else if (Character.isDigit(inputWS.charAt(0))) {
            numberEvaluate.setEvaluate(new ArabNumberEvaluate());
            return input + " = " + numberEvaluate.execute(input).interpret();
        } else if (Utils.isPresentInRom(Character.toString(inputWS.charAt(0)))) {
            numberEvaluate.setEvaluate(new RomNumberEvaluate());
            return input + " = " + Utils.arabToRom(numberEvaluate.execute(input).interpret());
        } else {
            throw new IllegalArgumentException("Не верные входные данные!");
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение типа - '(1 + 3 или I + VI)': ");
        String str = in.nextLine();
        System.out.println(calc(str));
    }
}