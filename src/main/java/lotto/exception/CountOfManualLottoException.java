package lotto.exception;

public class CountOfManualLottoException extends IllegalArgumentException {

    public static final String COUNT_OF_MANUAL_LOTTO_ONLY_POSITIVE_ERROR_MESSAGE = "구매할 개수는 음수가 될 수 없습니다.";
    public static final String COUNT_OF_MANUAL_LOTTO_LIMIT_ERROR_MESSAGE = "투입금액보다 많은 로또를 구매할 수 없습니다.";

    public CountOfManualLottoException(String message) {
        super(message);
    }
}
