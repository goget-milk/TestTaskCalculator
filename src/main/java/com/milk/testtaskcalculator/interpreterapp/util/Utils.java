package com.milk.testtaskcalculator.interpreterapp.util;

import com.milk.testtaskcalculator.interpreterapp.enumeration.RomNum;

import java.util.List;

public class Utils {
    public static int romToArab(String str) {
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

    public static String arabToRom(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException("результат вычисления: " +number+ " числа меньше 1 или больше 4000 в римском счислении не обрабатываются");
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

    public static boolean isPresentInRom(String ch) {
        for (RomNum val : RomNum.values()) {
            if (val.toString().equals(ch)) {
                return true;
            }
        }
        return false;
    }
}