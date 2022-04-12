package edu.javagroup.jcalc.digits;

import edu.javagroup.jcalc.lines.LineParsing;

/**
 * The {@code Division} class is intended to make math operation of addition.
 *
 * @author Drozdovskaya Anna
 * @version 1.0
 * @see java.lang.Integer
 * @see java.lang.Double
 * @see java.lang.String
 * @see Round
 */

public class Division {

    public static String division(String number1, String number2) {
        if (!number1.contains(".") && !number2.contains(".")) {
            return String.valueOf(division(LineParsing.getDouble(number1), LineParsing.getInteger(number2)));
        }
        if (!number1.contains(".") && number2.contains(".")) {
            return String.valueOf(division(LineParsing.getInteger(number1), LineParsing.getDouble(number2)));
        }
        if (number1.contains(".") && !number2.contains(".")) {
            return String.valueOf(division(LineParsing.getDouble(number1), LineParsing.getInteger(number2)));
        }
        if (number1.contains(".") && number2.contains(".")) {
            return String.valueOf(division(LineParsing.getDouble(number1), LineParsing.getDouble(number2)));
        }
        return "";
    }

    private static Double division(double number1, double number2) {
        return Round.round(number1 / number2);
    }

    private static Double division(double number1, int number2) {
        return Round.round(number1 / number2);
    }

    private static Double division(int number1, double number2) {
        return Round.round(number1 / number2);
    }
}
