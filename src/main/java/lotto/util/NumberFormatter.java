package lotto.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatter {

    private static final NumberFormat MONEY_FORMAT = NumberFormat.getNumberInstance(Locale.KOREA);
    private static final DecimalFormat RETURN_RATIO_FORMAT = new DecimalFormat("#,##0.00");

    private NumberFormatter() {
    }

    public static String formatMoney(final long moneyAmount) {
        return MONEY_FORMAT.format(moneyAmount);
    }

    public static String formatReturnRatio(final double returnRatio) {
        return RETURN_RATIO_FORMAT.format(returnRatio);
    }

}
