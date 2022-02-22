package lotto.exception;

public class MoneyOnlyNaturalNumberException extends IllegalArgumentException{

    private static final String MONEY_ONLY_NATURAL_NUMBER_ERROR_MESSAGE = "구입금액은 자연수만 가능합니다.";

    public MoneyOnlyNaturalNumberException() {
        super(MONEY_ONLY_NATURAL_NUMBER_ERROR_MESSAGE);
    }
}
