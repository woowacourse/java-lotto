package view;

import model.Money;

public class MoneyParser extends Parser<Money> {
    static final String INVALID_MONEY_FORMAT_MESSAGE = "비정상적인 금액 형식입니다. 예) 1000";
    private static final String REGEX_ZERO = "0";
    private static final String MONEY_REGEX = REGEX_BEGINNING + REGEX_ONE_TO_NINE + REGEX_ZERO_TO_NINE
            + REGEX_ASTERISK + REGEX_ZERO + repeatRegex(3) + REGEX_END;

    public MoneyParser() {
        super(MONEY_REGEX, INVALID_MONEY_FORMAT_MESSAGE);
    }

    @Override
    protected Money convert(String text) {
        return new Money(Integer.parseInt(text));
    }
}
