package lotto.exception;

public class MoneyException extends IllegalArgumentException {

    public static final String MONEY_UNIT_ERROR_MESSAGE = "투입금액은 1000원 단위만 가능합니다.";
    public static final String MONEY_ONLY_POSITIVE_NUMBER_ERROR_MESSAGE = "투입금액이 음수일 수 없습니다.";
    public static final String MONEY_SPENT_LIMIT_ERROR_MESSAGE = "투입한 금액을 모두 사용했습니다.";

    public MoneyException(String message) {
        super(message);
    }
}
