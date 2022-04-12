package edu.javagroup.jcalc.lines;

/**
 * The {@code LinePreparing} class is intended for preparing incoming String data for further math operations.
 *
 * @author Gorobets Galina
 * @version 1.4
 * @see java.lang.String
 * @see java.lang.StringBuilder
 */

public class LinePreparing {

    static String linePreparing(String line) {
        if (line.contains(" ")) {
            line = LinePreparing.removeSpaces(line);
        }
        if (line.contains(",")) {
            line = LinePreparing.replaceCommas(line);
        }
        line = LinePreparing.leaveMathSymbol(line);
        if (line.startsWith("+")) {
            line = line.substring(1);
        }
        return line;
    }

    private static String removeSpaces(String line) {
        return line.replace(" ", "");
    }

    private static String replaceCommas(String line) {
        return line.replace(",", ".");
    }

    private static String leaveMathSymbol(String line) {
        String source = "0123456789./*-+()";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (source.contains(String.valueOf(line.charAt(i)))) {
                stringBuilder.append(line.charAt(i));
            }
        }
        return trimTails(stringBuilder.toString());
    }

    private static String trimTails(String line) {
        int firstSymbolIndex = 0;
        int lastSymbolIndex = 0;
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                if (i != 0 && line.charAt(i - 1) == '-') {
                    firstSymbolIndex = i - 1;
                    break;
                } else {
                    firstSymbolIndex = i;
                    break;
                }
            }
        }
        for (int i = line.length() - 1; i >= 0; i--) {
            if (Character.isDigit(line.charAt(i)) || line.charAt(i) == ')') {
                lastSymbolIndex = i;
                break;
            }
        }
        line = lastSymbolIndex != line.length()
                ? line.substring(firstSymbolIndex, lastSymbolIndex + 1)
                : line.substring(firstSymbolIndex);
        return removeDuplicates(line);
    }

    private static String removeDuplicates(String line) {
        while (line.contains("++")
                | line.contains("--")
                | line.contains("**")
                | line.contains("//")
                | line.contains("+-")
                | line.contains("-+")
                | line.contains(")(")
                | line.contains("()")) {
            line = line.replace("++", "+").replace("--", "+")
                    .replace("**", "*").replace("//", "/")
                    .replace("+-", "+").replace("-+", "+")
                    .replace(")(", ")*(").replace("()", "");
        }
        return removeRoundBrackets(line);
    }

    private static String removeRoundBrackets(String line) {
        return line;
    }
}






    





