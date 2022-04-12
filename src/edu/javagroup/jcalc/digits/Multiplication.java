package edu.javagroup.jcalc.digits;

import edu.javagroup.jcalc.lines.LineParsing;

/**
 * The {@code Addition} class is intended to make math operation of multiplication.
 *
 * @author Drozdovskaya Anna
 * @version 1.0
 * @see java.lang.Integer
 * @see java.lang.Double
 * @see java.lang.String
 * @see Round
 */

public class Multiplication {

    public static String multiplication(String number1, String number2) {
        if (!number1.contains(".") && !number2.contains(".")) {
            return String.valueOf(multiplication(LineParsing.getInteger(number1), LineParsing.getInteger(number2)));
        }
        if (!number1.contains(".") && number2.contains(".")) {
            return String.valueOf(
                    Round.round(multiplication(LineParsing.getInteger(number1), LineParsing.getDouble(number2))));
        }
        if (number1.contains(".") && !number2.contains(".")) {
            return String.valueOf(
                    Round.round(multiplication(LineParsing.getDouble(number1), LineParsing.getInteger(number2))));
        }
        if (number1.contains(".") && number2.contains(".")) {
            return String.valueOf(
                    Round.round(multiplication(LineParsing.getDouble(number1), LineParsing.getDouble(number2))));
        }
        return "";
    }

    private static Integer multiplication(int number1, int number2) {
        return number1 * number2;
    }

    private static Double multiplication(double number1, double number2) {
        return number1 * number2;
    }

    private static Double multiplication(double number1, int number2) {
        return number1 * number2;
    }

    private static Double multiplication(int number1, double number2) {
        return number1 * number2;
    }
}
