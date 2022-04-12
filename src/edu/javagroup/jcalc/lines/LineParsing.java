package edu.javagroup.jcalc.lines;

/**
 * The {@code LineParsing} class is intended for extraction of numbers and math operators from the String data.
 *
 * @author Drozdovskaya Anna
 * @version 1.1
 * @see java.lang.String
 */

public class LineParsing {

    static String findFirstMathSymbol(String line) {

        if (line.startsWith("-")) {
            line = line.substring(1);
        }
        if (line.contains("*") || line.contains("/")) {
            return LineParsing.findFirstMathSymbol(line, 1);
        }
        return line.contains("+") || line.contains("-")
                ? LineParsing.findFirstMathSymbol(line, 2) : "";
    }

    private static String findFirstMathSymbol(String line, int priority) {
        if (priority == 1) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '*' || line.charAt(i) == '/') {
                    return String.valueOf(line.charAt(i));
                }
            }
        }
        if (priority == 2) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '+' || line.charAt(i) == '-') {
                    return String.valueOf(line.charAt(i));
                }
            }
        }
        return "";
    }

    static String getNumberFromLeftPart(String line, int mathOperatorIndex) {
        String leftPart = line.substring(0, mathOperatorIndex);
        for (int i = leftPart.length() - 1; i >= 0; i--) {
            String source = "0123456789.-";
            if (!source.contains(String.valueOf(leftPart.charAt(i)))) {
                leftPart = leftPart.substring(i + 1);
                break;
            }
            if (leftPart.charAt(i) == '-') {
                leftPart = leftPart.substring(i);
                break;
            }
        }
        return leftPart;
    }

    static String getNumberFromRightPart(String line, int mathOperatorIndex) {
        String rightPart = line.substring(mathOperatorIndex + 1);
        StringBuilder rightNumber = new StringBuilder();
        for (int i = 0; i < rightPart.length(); i++) {
            if (Character.isDigit(rightPart.charAt(i)) | line.charAt(i) == '.') {
                rightNumber.append(rightPart.charAt(i));
            } else {
                break;
            }
        }
        return rightNumber.toString();
    }

    public static Integer getInteger(String line) {
        return Integer.parseInt(line);
    }

    public static Double getDouble(String line) {
        return Double.parseDouble(line);
    }
}
