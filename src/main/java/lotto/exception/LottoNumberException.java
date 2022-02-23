package lotto.exception;

public class LottoNumberException extends IllegalArgumentException {

    public static final String LOTTO_NUMBER_NATURAL_NUMBER_ERROR_MESSAGE = "로또 번호는 자연수여야 합니다.";
    public static final String LOTTO_NUMBER_RANGE_ERROR_MESSAGE = "로또 번호는 1 ~ 45 사이여야 합니다.";

    public LottoNumberException(String message) {
        super(message);
    }
}
