package lotto.exception;

public class LottoPurchaseMoneyException extends IllegalArgumentException {

    public static final String INPUT_NOT_POSITIVE_NUMBER_ERROR_MESSAGE = "구입금액은 양수여야 합니다.";
    public static final String MONEY_UNIT_ERROR_MESSAGE = "구입금액은 1000원 단위만 가능합니다.";

    public LottoPurchaseMoneyException(String message) {
        super(message);
    }
}
