package edu.javagroup.jcalc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class JCalcTest {

    public static final Map<String, Class> CLASS_MAP;

    static {

        Map<String, Class> map = new LinkedHashMap<>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;

        try {
            Class roundClass = classLoader.loadClass("edu.javagroup.jcalc.digits.Round");
            if (roundClass != null) {
                map.put("0", roundClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class additionClass = classLoader.loadClass("edu.javagroup.jcalc.digits.Addition");
            if (additionClass != null) {
                map.put("1", additionClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class divisionClass = classLoader.loadClass("edu.javagroup.jcalc.digits.Division");
            if (divisionClass != null) {
                map.put("2", divisionClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class multiplicationClass = classLoader.loadClass("edu.javagroup.jcalc.digits.Multiplication");
            if (multiplicationClass != null) {
                map.put("3", multiplicationClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class subtractionClass = classLoader.loadClass("edu.javagroup.jcalc.digits.Subtraction");
            if (subtractionClass != null) {
                map.put("4", subtractionClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class lineCheckClass = classLoader.loadClass("edu.javagroup.jcalc.lines.LineCheck");
            if (lineCheckClass != null) {
                map.put("5", lineCheckClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class lineOperationClass = classLoader.loadClass("edu.javagroup.jcalc.lines.LineOperation");
            if (lineOperationClass != null) {
                map.put("6", lineOperationClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class lineParsingClass = classLoader.loadClass("edu.javagroup.jcalc.lines.LineParsing");
            if (lineParsingClass != null) {
                map.put("7", lineParsingClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class linePreparingClass = classLoader.loadClass("edu.javagroup.jcalc.lines.LinePreparing");
            if (linePreparingClass != null) {
                map.put("8", linePreparingClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        CLASS_MAP = Collections.unmodifiableMap(map);
    }

    //------------------------------------------------------------------------------------------------------------------

    public void startTest() {
        if (!CLASS_MAP.isEmpty()) {
            System.out.println("--- Выбери класс ---");
            for (Map.Entry<String, Class> item : CLASS_MAP.entrySet()) {
                System.out.println(item.getKey() + " - " + item.getValue().getSimpleName());
            }
            System.out.print("твой выбор: ");
            String classMenuItem = getMenuItem();
            Class testClass = CLASS_MAP.getOrDefault(classMenuItem, null);
            if (testClass == null) {
                System.out.println("Не найден искомый класс, наверное не расскоментирован в JCalcTest или ошибка выбора");
            } else {
                testClass(testClass);
            }
        } else {
            System.out.println("Ни один класс для тестов не найден");
        }
    }

    private String getMenuItem() {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return "";
    }

    private void testClass(Class testClass) {
        switch (testClass.getSimpleName()) {
            case "Round":
                testRound(testClass);
                break;
            case "Addition":
                testAddition(testClass);
                break;
            case "Division":
                testDivision(testClass);
                break;
            case "Multiplication":
                testMultiplication(testClass);
                break;
            case "Subtraction":
                testSubtraction(testClass);
                break;
            case "LineCheck":
                testLineCheck(testClass);
                break;
            case "LineOperation":
                testLineOperation(testClass);
                break;
            case "LineParsing":
                testLineParsing(testClass);
                break;
            case "LinePreparing":
                testLinePreparing(testClass);
                break;
            default:
                System.out.println("Не найден искомый класс для теста методов");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private void testRound(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");

        double result = (double) testMethod(testClass, "round", new Object[]{1.10601});
        System.out.println("Метод: round - отправлено: 1.10601, получено: " + result + " => " + (result == 1.11 ? "УСПЕХ" : "ПРОВАЛ"));
    }

    private void testAddition(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");

        String result1 = (String) testMethod(testClass, "addition", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"3", "2"});
        System.out.println("Метод: addition - отправлено: 3 + 2, получено: " + result1 + " => " + (result1.equals("5") ? "УСПЕХ" : "ПРОВАЛ"));

        String result2 = (String) testMethod(testClass, "addition", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"3", "2.34"});
        System.out.println("Метод: addition - отправлено: 3 + 2.34, получено + " + result2 + " => " + (result2.equals("5.34") ? "УСПЕХ" : "ПРОВАЛ"));

        String result3 = (String) testMethod(testClass, "addition", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"3.34", "2"});
        System.out.println("Метод: addition - отправлено: 3.34 + 2, получено + " + result3 + " => " + (result3.equals("5.34") ? "УСПЕХ" : "ПРОВАЛ"));

        String result4 = (String) testMethod(testClass, "addition", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"3.34", "2.11"});
        System.out.println("Метод: addition - отправлено: 3.34 + 2.11, получено + " + result4 + " => " + (result4.equals("5.45") ? "УСПЕХ" : "ПРОВАЛ"));
    }

    private void testDivision(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");

        String result1 = (String) testMethod(testClass, "division", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"5", "3"});
        System.out.println("Метод: division - отправлено: 5 / 3, получено: " + result1 + " => " + (result1.equals("1.67") ? "УСПЕХ" : "ПРОВАЛ"));

        String result2 = (String) testMethod(testClass, "division", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"2", "1.1"});
        System.out.println("Метод: division - отправлено: 2 / 1.1, получено + " + result2 + " => " + (result2.equals("1.82") ? "УСПЕХ" : "ПРОВАЛ"));

        String result3 = (String) testMethod(testClass, "division", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"2.2", "3"});
        System.out.println("Метод: division - отправлено: 2.2 / 3, получено + " + result3 + " => " + (result3.equals("0.73") ? "УСПЕХ" : "ПРОВАЛ"));

        String result4 = (String) testMethod(testClass, "division", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"54.22", "18.02"});
        System.out.println("Метод: division - отправлено: 54.22 / 18.02, получено + " + result4 + " => " + (result4.equals("3.01") ? "УСПЕХ" : "ПРОВАЛ"));
    }

    private void testMultiplication(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");

        String result1 = (String) testMethod(testClass, "multiplication", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"5", "3"});
        System.out.println("Метод: multiplication - отправлено: 5 * 3, получено: " + result1 + " => " + (result1.equals("15") ? "УСПЕХ" : "ПРОВАЛ"));

        String result2 = (String) testMethod(testClass, "multiplication", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"2", "1.1"});
        System.out.println("Метод: multiplication - отправлено: 2 * 1.1, получено + " + result2 + " => " + (result2.equals("2.2") ? "УСПЕХ" : "ПРОВАЛ"));

        String result3 = (String) testMethod(testClass, "multiplication", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"2.2", "3"});
        System.out.println("Метод: multiplication - отправлено: 2.2 * 3, получено + " + result3 + " => " + (result3.equals("6.6") ? "УСПЕХ" : "ПРОВАЛ"));

        String result4 = (String) testMethod(testClass, "multiplication", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"54.22", "18.02"});
        System.out.println("Метод: multiplication - отправлено: 54.22 * 18.02, получено + " + result4 + " => " + (result4.equals("977.04") ? "УСПЕХ" : "ПРОВАЛ"));
    }

    private void testSubtraction(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");

        String result1 = (String) testMethod(testClass, "subtraction", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"5", "3"});
        System.out.println("Метод: subtraction - отправлено: 5 - 3, получено: " + result1 + " => " + (result1.equals("2") ? "УСПЕХ" : "ПРОВАЛ"));

        String result2 = (String) testMethod(testClass, "subtraction", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"2", "1.1"});
        System.out.println("Метод: subtraction - отправлено: 2 - 1.1, получено + " + result2 + " => " + (result2.equals("0.9") ? "УСПЕХ" : "ПРОВАЛ"));

        String result3 = (String) testMethod(testClass, "subtraction", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"2.2", "3"});
        System.out.println("Метод: subtraction - отправлено: 2.2 - 3, получено + " + result3 + " => " + (result3.equals("-0.8") ? "УСПЕХ" : "ПРОВАЛ"));

        String result4 = (String) testMethod(testClass, "subtraction", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"54.22", "18.02"});
        System.out.println("Метод: subtraction - отправлено: 54.22 - 18.02, получено + " + result4 + " => " + (result4.equals("36.2") ? "УСПЕХ" : "ПРОВАЛ"));
    }

    private void testLineCheck(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");

        boolean result1 = (boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"*1"});
        System.out.println("Метод: isMathSymbolsCorrect - отправлено: *1, получено: " + result1 + " => " + (!result1 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result2 = (boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"1*"});
        System.out.println("Метод: isMathSymbolsCorrect - отправлено: 1*, получено: " + result2 + " => " + (!result2 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result3 = (boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"/1"});
        System.out.println("Метод: isMathSymbolsCorrect - отправлено: /1, получено: " + result3 + " => " + (!result3 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result4 = (boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"1/"});
        System.out.println("Метод: isMathSymbolsCorrect - отправлено: 1/, получено: " + result4 + " => " + (!result4 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result5 = (boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"+1"});
        System.out.println("Метод: isMathSymbolsCorrect - отправлено: +1, получено: " + result5 + " => " + (!result5 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result6 = (boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"1+"});
        System.out.println("Метод: isMathSymbolsCorrect - отправлено: 1+, получено: " + result6 + " => " + (!result6 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result7 = (boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"-1"});
        System.out.println("Метод: isMathSymbolsCorrect - отправлено: -1, получено: " + result7 + " => " + (result7 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result8 = (boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"1-"});
        System.out.println("Метод: isMathSymbolsCorrect - отправлено: 1-, получено: " + result8 + " => " + (!result8 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result9 = (boolean) testMethod(testClass, "isRoundBracketsPositionsCorrect", new Object[]{"1)2(3"});
        System.out.println("Метод: isRoundBracketsPositionsCorrect - отправлено: 1)2(3, получено: " + result9 + " => " + (!result9 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result10 = (boolean) testMethod(testClass, "isRoundBracketsPositionsCorrect", new Object[]{"1(2)3"});
        System.out.println("Метод: isRoundBracketsPositionsCorrect - отправлено: 1(2)3, получено: " + result10 + " => " + (result10 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result11 = (boolean) testMethod(testClass, "isRoundBracketsCountCorrect", new Object[]{"1(2)3)4"});
        System.out.println("Метод: isRoundBracketsCountCorrect - отправлено: 1(2)3)4, получено: " + result11 + " => " + (!result11 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result12 = (boolean) testMethod(testClass, "isRoundBracketsCountCorrect", new Object[]{"1(2(3)4)5"});
        System.out.println("Метод: isRoundBracketsCountCorrect - отправлено: 1(2(3)4)5, получено: " + result12 + " => " + (result12 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result13 = (boolean) testMethod(testClass, "isRoundBracketsCorrect", new Object[]{"1234"});
        System.out.println("Метод: isRoundBracketsCorrect - отправлено: 1234, получено: " + result13 + " => " + (result13 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result14 = (boolean) testMethod(testClass, "isRoundBracketsCorrect", new Object[]{"1(2(3)"});
        System.out.println("Метод: isRoundBracketsCorrect - отправлено: 1(2(3), получено: " + result14 + " => " + (!result14 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result15 = (boolean) testMethod(testClass, "isRoundBracketsCorrect", new Object[]{"1(2(3)4)5"});
        System.out.println("Метод: isRoundBracketsCorrect - отправлено: 1(2(3)4)5, получено: " + result15 + " => " + (result15 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result16 = (boolean) testMethod(testClass, "isLineCorrect", new Object[]{"1234"});
        System.out.println("Метод: isLineCorrect - отправлено: 1234, получено: " + result16 + " => " + (result16 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result17 = (boolean) testMethod(testClass, "isLineCorrect", new Object[]{"1(2(3)"});
        System.out.println("Метод: isLineCorrect - отправлено: 1(2(3), получено: " + result17 + " => " + (!result17 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result18 = (boolean) testMethod(testClass, "isLineCorrect", new Object[]{"1(2(3)4)5"});
        System.out.println("Метод: isLineCorrect - отправлено: 1(2(3)4)5, получено: " + result18 + " => " + (result18 ? "УСПЕХ" : "ПРОВАЛ"));
    }

    private void testLineOperation(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");

        boolean result1 = (boolean) testMethod(testClass, "isFinalNumber", new Object[]{"-1.2+3.4"});
        System.out.println("Метод: isFinalNumber - отправлено: -1.2+3.4, получено: " + result1 + " => " + (!result1 ? "УСПЕХ" : "ПРОВАЛ"));

        // todo: kaa: внести правки по поводу точек
//        boolean result2 = (boolean) testMethod(testClass, "isFinalNumber", new Object[]{"-.1.2."});
//        System.out.println("Метод: isFinalNumber - отправлено: -.1.2., получено: " + result2 + " => " + (!result2 ? "УСПЕХ" : "ПРОВАЛ"));

        boolean result3 = (boolean) testMethod(testClass, "isFinalNumber", new Object[]{"-1.2"});
        System.out.println("Метод: isFinalNumber - отправлено: -1.2, получено: " + result3 + " => " + (result3 ? "УСПЕХ" : "ПРОВАЛ"));

        String result4 = (String) testMethod(testClass, "collectLines", new Object[]{"1+2+4", "3", 1});
        System.out.println("Метод: collectLines - отправлено: 1+2+4 и 3 и 1, получено: " + result4 + " => " + (result4.equals("3+4") ? "УСПЕХ" : "ПРОВАЛ"));

        String result5 = (String) testMethod(testClass, "collectLines", new Object[]{"1+(2+3)+4", "5", 2, 6});
        System.out.println("Метод: collectLines - отправлено: 1+(2+3)+4 и 5 и 2 и 6, получено: " + result5 + " => " + (result5.equals("1+5+4") ? "УСПЕХ" : "ПРОВАЛ"));

        String result6 = (String) testMethod(testClass, "subtraction", new Object[]{"1+2-3+4", 3});
        System.out.println("Метод: subtraction - отправлено: 1+2-3+4 и 3, получено: " + result6 + " => " + (result6.equals("-1") ? "УСПЕХ" : "ПРОВАЛ"));

        String result7 = (String) testMethod(testClass, "addition", new Object[]{"1-2+3-4", 3});
        System.out.println("Метод: addition - отправлено: 1-2+3-4 и 3, получено: " + result7 + " => " + (result7.equals("1") ? "УСПЕХ" : "ПРОВАЛ"));

        String result8 = (String) testMethod(testClass, "division", new Object[]{"1-2/3-4", 3});
        System.out.println("Метод: division - отправлено: 1-2/3-4 и 3, получено: " + result8 + " => " + (result8.equals("-0.67") ? "УСПЕХ" : "ПРОВАЛ"));

        String result9 = (String) testMethod(testClass, "multiplication", new Object[]{"1-2*3-4", 3});
        System.out.println("Метод: multiplication - отправлено: 1-2*3-4 и 3, получено: " + result9 + " => " + (result9.equals("-6") ? "УСПЕХ" : "ПРОВАЛ"));

        String result10 = (String) testMethod(testClass, "getResultWithoutRoundBrackets", new Object[]{"1+4"});
        System.out.println("Метод: getResultWithoutRoundBrackets - отправлено: 1+4, получено: " + result10 + " => " + (result10.equals("5") ? "УСПЕХ" : "ПРОВАЛ"));

        String result11 = (String) testMethod(testClass, "getResultWithRoundBrackets", new Object[]{"1+(2+3)+4"});
        System.out.println("Метод: getResultWithRoundBrackets - отправлено: 1+(2+3)+4, получено: " + result11 + " => " + (result11.equals("10") ? "УСПЕХ" : "ПРОВАЛ"));

        // todo: нужно больше вариаций
        String result12 = (String) testMethod(testClass, "getResult", new Object[]{"1+1"});
        System.out.println("Метод: getResult - отправлено: 1+1, получено: " + result12 + " => " + (result12.equals("2") ? "УСПЕХ" : "ПРОВАЛ"));

        String result13 = (String) testMethod(testClass, "concatLines", new Object[]{"1", "2"});
        System.out.println("Метод: concatLines - отправлено: 1, 2, получено: " + result13 + " => " + (result13.equals("12") ? "УСПЕХ" : "ПРОВАЛ"));

        String result14 = (String) testMethod(testClass, "addMinusPrefix", new Object[]{"1"});
        System.out.println("Метод: addMinusPrefix - отправлено: 1, получено: " + result14 + " => " + (result14.equals("-1") ? "УСПЕХ" : "ПРОВАЛ"));

        String result15 = (String) testMethod(testClass, "removeLastSymbol", new Object[]{"1+2+34"});
        System.out.println("Метод: removeLastSymbol - отправлено: 1+2+34, получено: " + result15 + " => " + (result15.equals("1+2+3") ? "УСПЕХ" : "ПРОВАЛ"));
    }

    private void testLineParsing(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");

        String result1 = (String) testMethod(testClass, "getNumberFromRightPart", new Object[]{"1.2+3.4*5.6/7.8", 7});
        System.out.println("Метод: getNumberFromRightPart - отправлено: 1.2+3.4*5.6/7.8 и 7, получено: " + result1 + " => " + (result1.equals("5.6") ? "УСПЕХ" : "ПРОВАЛ"));

        String result2 = (String) testMethod(testClass, "getNumberFromLeftPart", new Object[]{"1.2+3.4*5.6/7.8", 7});
        System.out.println("Метод: getNumberFromLeftPart - отправлено: 1.2+3.4*5.6/7.8 и 7, получено: " + result2 + " => " + (result2.equals("3.4") ? "УСПЕХ" : "ПРОВАЛ"));

        String result3 = (String) testMethod(testClass, "findFirstMathSymbol", new Object[]{"1.2+3.4*5.6/7.8", 1});
        System.out.println("Метод: findFirstMathSymbol - отправлено: 1.2+3.4*5.6/7.8 и 1, получено: " + result3 + " => " + (result3.equals("*") ? "УСПЕХ" : "ПРОВАЛ"));

        String result4 = (String) testMethod(testClass, "findFirstMathSymbol", new Object[]{"1.2+3.4*5.6/7.8", 2});
        System.out.println("Метод: findFirstMathSymbol - отправлено: 1.2+3.4*5.6/7.8 и 2, получено: " + result4 + " => " + (result4.equals("+") ? "УСПЕХ" : "ПРОВАЛ"));

        String result5 = (String) testMethod(testClass, "findFirstMathSymbol", new Object[]{"1.2+3.4*5.6/7.8"});
        System.out.println("Метод: findFirstMathSymbol - отправлено: 1.2+3.4*5.6/7.8, получено: " + result5 + " => " + (result5.equals("*") ? "УСПЕХ" : "ПРОВАЛ"));

        String result6 = (String) testMethod(testClass, "findFirstMathSymbol", new Object[]{"1.2+3.4-5.6"});
        System.out.println("Метод: findFirstMathSymbol - отправлено: 1.2+3.4-5.6, получено: " + result6 + " => " + (result6.equals("+") ? "УСПЕХ" : "ПРОВАЛ"));
    }

    private void testLinePreparing(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");

        // todo: kaa: внести правки в тз
//        String result1 = (String) testMethod(testClass, "removeRoundBrackets", new Object[]{""});
//        System.out.println("Метод: removeRoundBrackets - отправлено: '', получено: " + result1 + " => " + (result1.equals("") ? "УСПЕХ" : "ПРОВАЛ"));

        String result2 = (String) testMethod(testClass, "removeDuplicates", new Object[]{"++++--+--///*//***()-)(++**"});
        System.out.println("Метод: removeDuplicates - отправлено: ++++--+--///*//***()-)(++**, получено: " + result2 + " => " + (result2.equals("+/*/*-)*(+*") ? "УСПЕХ" : "ПРОВАЛ"));

        String result3 = (String) testMethod(testClass, "trimTails", new Object[]{"++++--+--1--1++//**"});
        System.out.println("Метод: trimTails - отправлено: ++++--+--1--1++//**, получено: " + result3 + " => " + (result3.equals("-1+1") ? "УСПЕХ" : "ПРОВАЛ"));

        String result4 = (String) testMethod(testClass, "leaveMathSymbol", new Object[]{"+aaaa-1b+b1+cccc"});
        System.out.println("Метод: leaveMathSymbol - отправлено: +aaaa-1b--b1+cccc, получено: " + result4 + " => " + (result4.equals("-1+1") ? "УСПЕХ" : "ПРОВАЛ"));

        String result5 = (String) testMethod(testClass, "replaceCommas", new Object[]{"1,2+3,4"});
        System.out.println("Метод: replaceCommas - отправлено: 1,2+3,4, получено: " + result5 + " => " + (result5.equals("1.2+3.4") ? "УСПЕХ" : "ПРОВАЛ"));

        String result6 = (String) testMethod(testClass, "removeSpaces", new Object[]{"1 + 2 - 4"});
        System.out.println("Метод: removeSpaces - отправлено: 1 + 2 - 4, получено: " + result6 + " => " + (result6.equals("1+2-4") ? "УСПЕХ" : "ПРОВАЛ"));

        // todo: kaa: внести правки в тз по поводу скобок
        String result7 = (String) testMethod(testClass, "linePreparing", new Object[]{"-- // ++ -- 1 ++ ( ) ++ ( 2 - 4 ) ( 4 ** 5 ) + 4 ++"});
        System.out.println("Метод: linePreparing - отправлено: -- // ++ -- 1 ++ ( ) ++ ( 2 - 4 ) ( 4 ** 5 ) + 4 ++, получено: " + result7 + " => " + (result7.equals("-1+(2-4)*(4*5)+4") ? "УСПЕХ" : "ПРОВАЛ"));
    }

    //------------------------------------------------------------------------------------------------------------------

    private Object testMethod(Class testClass, String methodName, Object[] parameterValueArray) {
        try {
            Method[] methodArray = testClass.getDeclaredMethods();
            for (Method item : methodArray) {
                Class[] parameterTypeArray = item.getParameterTypes();
                if (item.getName().equals(methodName) && parameterTypeArray.length == parameterValueArray.length) {
                    Method method = testClass.getDeclaredMethod(methodName, parameterTypeArray);
                    method.setAccessible(true);
                    return method.invoke(testClass, parameterValueArray);
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
        throw new RuntimeException("Возникла ошибка вызова метода");
    }

    private Object testMethod(Class testClass, String methodName, Class[] parameterTypeArray, Object[] parameterValueArray) {
        try {
            Method[] methodArray = testClass.getDeclaredMethods();
            for (Method item : methodArray) {
                if (item.getName().equals(methodName) && parameterTypeArray.length == parameterValueArray.length) {
                    Method method = testClass.getDeclaredMethod(methodName, parameterTypeArray);
                    method.setAccessible(true);
                    return method.invoke(testClass, parameterValueArray);
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
        throw new RuntimeException("Возникла ошибка вызова метода");
    }
}
