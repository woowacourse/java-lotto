package lotto.domain.factory;

import lotto.domain.lotto.Money;

public class MoneyFactory {

    public static Money valueOf(String text) {
        return new Money(toInt(text));
    }

    private static int toInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자여야 합니다.");
        }
    }
}
