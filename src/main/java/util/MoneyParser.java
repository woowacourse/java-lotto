package util;

import domain.Money;

public class MoneyParser {
    public static Money parse(String input) {
        int price = Integer.parseUnsignedInt(input);
        return Money.from(price);
    }
}
