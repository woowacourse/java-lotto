package lotto.util;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatter {

    private NumberFormatter() {
    }

    public static String formatMoney(final long moneyAmount) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
        return numberFormat.format(moneyAmount);
    }

}
