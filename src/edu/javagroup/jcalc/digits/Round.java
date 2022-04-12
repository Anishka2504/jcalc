package edu.javagroup.jcalc.digits;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The {@code Round} class is intended for rounding numbers to two decimal places.
 *
 * @author Drozdovskaya Anna
 * @version 1.0
 * @see java.math.BigDecimal
 */

public class Round {

    static final int ROUND_LIMIT = 2;

    public static double round(double number) {
        return new BigDecimal(number).setScale(ROUND_LIMIT, RoundingMode.HALF_UP).doubleValue();
    }
}
