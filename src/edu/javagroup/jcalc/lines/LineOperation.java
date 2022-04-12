package edu.javagroup.jcalc.lines;

import edu.javagroup.jcalc.digits.Addition;
import edu.javagroup.jcalc.digits.Division;
import edu.javagroup.jcalc.digits.Multiplication;
import edu.javagroup.jcalc.digits.Subtraction;

import javax.swing.*;

/**
 * The {@code LineOperation} class is intended to get the result of performing different math operations such as
 * addition, division, multiplication and subtraction with numbers and expressions with and without brackets.
 *
 * @author Drozdovskaya Anna
 * @version 1.3
 * @see java.lang.String
 * @see LinePreparing
 * @see LineCheck
 */

public class LineOperation {

    public static String removeLastSymbol(String line) {
        return (line.substring(0, line.length() - 1));
    }

    public static String addMinusPrefix(String line) {
        return "-" + line;
    }

    public static String concatLines(String firstLine, String lastLine) {
        return firstLine + lastLine;
    }

    public static String getResult(String line) {
        if (line == null || line.isEmpty()) {
            JOptionPane.showMessageDialog
                    (null, "Line is empty", "Error!", JOptionPane.ERROR_MESSAGE);
            return "";
        }
        line = LinePreparing.linePreparing(line);
        if (LineCheck.isLineCorrect(line)) {
            if (isFinalNumber(line)) {
                return line;
            }
        }
        return line.contains("(") || line.contains(")")
                ? getResultWithRoundBrackets(LinePreparing.linePreparing(line))
                : getResultWithoutRoundBrackets(LinePreparing.linePreparing(line));
    }

    private static String getResultWithRoundBrackets(String line) {
        while (line.contains("(") | line.contains(")") || !isFinalNumber(line)) {
            int indexOpenBracket = line.lastIndexOf('(');
            int indexCloseBracket = 0;
            for (int i = indexOpenBracket + 1; i < line.length(); i++) {
                if (line.charAt(i) == ')') {
                    indexCloseBracket = i;
                    break;
                }
            }
            String partBetweenBrackets = LinePreparing.linePreparing
                    (line.substring(indexOpenBracket + 1, indexCloseBracket));
            String resultBetweenBrackets = getResultWithoutRoundBrackets(partBetweenBrackets);
            line = collectLines(line, resultBetweenBrackets, indexOpenBracket, indexCloseBracket);
            if (!(line.contains("(") && line.contains(")")) || isFinalNumber(line)) {
                break;
            }
        }
        line = getResultWithoutRoundBrackets(line);
        return line;
    }

    private static String getResultWithoutRoundBrackets(String line) {
        while (line.contains("*") || line.contains("/") || line.contains("+")
                || line.contains("-") || !isFinalNumber(line)) {
            String mathOperator = line.startsWith("-")
                    ? LineParsing.findFirstMathSymbol(line.substring(1))
                    : LineParsing.findFirstMathSymbol(line);
            if (mathOperator.isEmpty()) {
                JOptionPane.showMessageDialog
                        (null, "Operator not found", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                return "";
            }
            int mathOperatorIndex = line.startsWith("-")
                    ? line.substring(1).indexOf(mathOperator) + 1 : line.indexOf(mathOperator);
            String result = "";
            if (line.charAt(mathOperatorIndex) == '*') {
                result = multiplication(line, mathOperatorIndex);
            }
            if (line.charAt(mathOperatorIndex) == '/') {
                result = division(line, mathOperatorIndex);
            }
            if (line.charAt(mathOperatorIndex) == '+') {
                result = addition(line, mathOperatorIndex);
            }
            if (line.charAt(mathOperatorIndex) == '-') {
                result = subtraction(line, mathOperatorIndex);
            }
            line = collectLines(line, result, mathOperatorIndex);
            if (line.contains("--")) {
                line = line.replace("--", "+");
            }
            if (line.contains(".")) {
                if (Double.parseDouble(line.substring(line.indexOf('.') + 1)) / 2 == 0) {
                    return line.substring(0, line.indexOf('.'));
                }
            }
            if (isFinalNumber(line)) {
                break;
            }

        }
        return line;
    }

    private static String multiplication(String line, int mathOperatorIndex) {
        String leftPart = LineParsing.getNumberFromLeftPart(line, mathOperatorIndex);
        String rightPart = LineParsing.getNumberFromRightPart(line, mathOperatorIndex);
        return Multiplication.multiplication(leftPart, rightPart);
    }

    private static String division(String line, int mathOperatorIndex) {
        String leftPart = LineParsing.getNumberFromLeftPart(line, mathOperatorIndex);
        String rightPart = LineParsing.getNumberFromRightPart(line, mathOperatorIndex);
        if (Integer.parseInt(rightPart) == 0 || Double.parseDouble(rightPart) == 0.0) {
            JOptionPane.showMessageDialog
                    (null, "Division by zero", "Error!", JOptionPane.ERROR_MESSAGE);
            return "";
        }
        return Division.division(leftPart, rightPart);
    }

    private static String addition(String line, int mathOperatorIndex) {
        String leftPart = LineParsing.getNumberFromLeftPart(line, mathOperatorIndex);
        String rightPart = LineParsing.getNumberFromRightPart(line, mathOperatorIndex);
        return Addition.addition(leftPart, rightPart);
    }

    private static String subtraction(String line, int mathOperatorIndex) {
        String leftPart = LineParsing.getNumberFromLeftPart(line, mathOperatorIndex);
        String rightPart = LineParsing.getNumberFromRightPart(line, mathOperatorIndex);
        return Subtraction.subtraction(leftPart, rightPart);
    }

    private static String collectLines(String line, String result, int mathOperatorIndex) {
        String leftNumber = LineParsing.getNumberFromLeftPart(line, mathOperatorIndex);
        String rightNumber = LineParsing.getNumberFromRightPart(line, mathOperatorIndex);
        String replacePart = leftNumber + line.charAt(mathOperatorIndex) + rightNumber;
        return line.replace(replacePart, result);
    }

    private static String collectLines(String line, String result, int indexOpenBracket, int indexCloseBracket) {
        String leftPart = line.substring(0, indexOpenBracket);
        String rightPart = line.endsWith(")") ? "" : line.substring(indexCloseBracket + 1);
        return leftPart + result + rightPart;
    }

    private static boolean isFinalNumber(String line) {
        if (line.startsWith("-")) {
            line = line.substring(1);
        }
        for (int i = 0; i < line.length(); i++) {
            if (!Character.isDigit(line.charAt(i)) & line.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }
}
