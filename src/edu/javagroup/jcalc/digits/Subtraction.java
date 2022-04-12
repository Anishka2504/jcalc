package edu.javagroup.jcalc.digits;

import edu.javagroup.jcalc.lines.LineParsing;

/**
 * The {@code Addition} class is intended to make math operation of subtraction.
 *
 * @author Drozdovskaya Anna
 * @version 1.0
 * @see java.lang.Integer
 * @see java.lang.Double
 * @see java.lang.String
 * @see Round
 */

public class Subtraction {

    public static String subtraction(String number1, String number2) {
        if (!number1.contains(".") && !number2.contains(".")) {
            return String.valueOf(subtraction(LineParsing.getInteger(number1), LineParsing.getInteger(number2)));
        }
        if (!number1.contains(".") && number2.contains(".")) {
            return String.valueOf(
                    Round.round(subtraction(LineParsing.getInteger(number1), LineParsing.getDouble(number2))));
        }
        if (number1.contains(".") && !number2.contains(".")) {
            return String.valueOf(
                    Round.round(subtraction(LineParsing.getDouble(number1), LineParsing.getInteger(number2))));
        }
        if (number1.contains(".") && number2.contains(".")) {
            return String.valueOf(
                    Round.round(subtraction(LineParsing.getDouble(number1), LineParsing.getDouble(number2))));
        }
        return "";
    }

    private static Integer subtraction(int number1, int number2) {
        return number1 - number2;
    }

    private static Double subtraction(double number1, double number2) {
        return number1 - number2;
    }

    private static Double subtraction(double number1, int number2) {
        return number1 - number2;
    }

    private static Double subtraction(int number1, double number2) {
        return number1 - number2;
    }
}
