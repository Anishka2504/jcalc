package edu.javagroup.jcalc.lines;

/**
 * The {@code LineCheck} class is intended for checking the possibility of using the line for further math operations.
 *
 * @author Brel Ilya
 * @version 1.0
 * @see java.lang.String
 */

public class LineCheck {

    static boolean isLineCorrect(String line) {
        return isRoundBracketsCorrect(line) && isMathSymbolsCorrect(line);
    }

    private static boolean isRoundBracketsCorrect(String line) {
        return !line.contains("(") && !line.contains(")") || isRoundBracketsCountCorrect(line);
    }

    private static boolean isRoundBracketsCountCorrect(String line) {
        int countOpenBracket = 0;
        int countCloseBracket = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '(') {
                countOpenBracket++;
            }
            if (line.charAt(i) == ')') {
                countCloseBracket++;
            }
        }
        return countCloseBracket == countOpenBracket && isRoundBracketsPositionsCorrect(line);
    }

    private static boolean isRoundBracketsPositionsCorrect(String line) {
        return !(line.indexOf(')') < line.indexOf('(') | line.lastIndexOf('(') > line.lastIndexOf(')'));
    }

    private static boolean isMathSymbolsCorrect(String line) {
        return isFirstMathSymbol(line, "*") && isFirstMathSymbol(line, "/")
                && isLastMathSymbol(line, "*") && isLastMathSymbol(line, "/")
                && isFirstMathSymbol(line, "+")
                && isLastMathSymbol(line, "+") && isLastMathSymbol(line, "-");
    }

    private static boolean isFirstMathSymbol(String line, String mathOperationSymbol) {
        return !line.startsWith(mathOperationSymbol);
    }

    private static boolean isLastMathSymbol(String line, String mathOperationSymbol) {
        return !line.endsWith(mathOperationSymbol);
    }
}
