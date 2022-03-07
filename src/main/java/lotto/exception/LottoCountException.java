package lotto.exception;

public class LottoCountException extends IllegalArgumentException {

    public static final String LOTTO_COUNT_NOT_NEGATIVE_NUMBER_ERROR = "구매할 로또 수가 음수여서는 안 됩니다.";
    public static final String LOTTO_COUNT_OVER_MONEY_ERROR = "구매할 로또 수가 구입금액을 초과합니다.";

    public LottoCountException(String message) {
        super(message);
    }
}
