package edu.javagroup.jcalc.digits;

import edu.javagroup.jcalc.lines.LineParsing;

/**
 * The {@code Addition} class is intended to make math operation of addition
 *
 * @author Drozdovskaya Anna
 * @version 1.0
 * @see java.lang.Integer
 * @see java.lang.Double
 * @see java.lang.String
 * @see Round
 */

public class Addition {

    public static String addition(String number1, String number2) {
        if (!number1.contains(".") && !number2.contains(".")) {
            return String.valueOf(addition(LineParsing.getInteger(number1), LineParsing.getInteger(number2)));
        }
        if (!number1.contains(".") && number2.contains(".")) {
            return String.valueOf(addition(LineParsing.getInteger(number1), LineParsing.getDouble(number2)));
        }
        if (number1.contains(".") && !number2.contains(".")) {
            return String.valueOf(addition(LineParsing.getDouble(number1), LineParsing.getInteger(number2)));
        }
        if (number1.contains(".") && number2.contains(".")) {
            return String.valueOf(addition(LineParsing.getDouble(number1), LineParsing.getDouble(number2)));
        }
        return "";
    }

    private static Integer addition(int number1, int number2) {
        return number1 + number2;
    }

    private static Double addition(double number1, double number2) {
        return Round.round(number1 + number2);
    }

    private static Double addition(double number1, int number2) {
        return Round.round(number1 + number2);
    }

    private static Double addition(int number1, double number2) {
        return Round.round(number1 + number2);
    }
}
