package view;

import model.Money;

public class MoneyParser extends Parser<Money> {
    static final String INVALID_MONEY_FORMAT_MESSAGE = "금액은 1000의 배수여야 합니다. 예) 10000";
    private static final String REGEX_ZERO = "0";
    private static final String MONEY_REGEX = REGEX_BEGINNING + REGEX_ONE_TO_NINE + REGEX_ZERO_TO_NINE
            + REGEX_ASTERISK + REGEX_ZERO + repeatRegex(3) + REGEX_END;

    @Override
    protected Money convert(String text) {
        return new Money(Integer.parseInt(text));
    }

    @Override
    protected String regex() {
        return MONEY_REGEX;
    }

    @Override
    protected String errorMessage() {
        return INVALID_MONEY_FORMAT_MESSAGE;
    }
}
