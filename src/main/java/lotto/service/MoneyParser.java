package lotto.service;

import lotto.domain.Money;

import java.util.regex.Pattern;

public class MoneyParser {
    static final Pattern MONEY_FORMAT = Pattern.compile("^[0-9]+$");

    public static Money parse(String input) {
        if (!MONEY_FORMAT.matcher(input).matches()) {
            throw new IllegalArgumentException("금액은 숫자로 입력해야 합니다.");
        }
        return new Money(Integer.parseInt(input));
    }
}
