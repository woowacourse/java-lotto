package lotto.view;

import lotto.model.Money;

public class MoneyParser extends Parser<Money> {

    static final String INVALID_MONEY_FORMAT_MESSAGE = "금액은 1000의 배수여야 합니다. 예) 10000";

    @Override
    protected Money convert(String text) {
        return new Money(Integer.parseInt(text));
    }

    @Override
    protected String regex() {
        return "^[1-9][0-9]*0{3}$";
    }

    @Override
    protected String errorMessage() {
        return INVALID_MONEY_FORMAT_MESSAGE;
    }
}
