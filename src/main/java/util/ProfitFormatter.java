package util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ProfitFormatter {

    private static final String PROFIT_PATTERN = "#.##";
    private static final DecimalFormat profitFormatter = new DecimalFormat(PROFIT_PATTERN);

    static {
        profitFormatter.setRoundingMode(RoundingMode.DOWN);
    }

    public static String apply(double number) {
        return profitFormatter.format(number);
    }
}
