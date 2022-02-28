package lotto.exception;

public class MoneyException extends IllegalArgumentException{

    public static final String MONEY_UNIT_ERROR_MESSAGE = "구입금액은 1000원 단위만 가능합니다.";
    public static final String MONEY_ONLY_POSITIVE_NUMBER_ERROR_MESSAGE = "구입금액이 음수일 수 없습니다.";

    public MoneyException(String message) {
        super(message);
    }
}
