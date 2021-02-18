package lotto.exception;

public class BonusBallScopeException extends RuntimeException {
    public static final String ERROR_MESSAGE = "보너스 볼은 1 ~ 45 사이의 수여야만 합니다.";

    public BonusBallScopeException() {
        super(ERROR_MESSAGE);
    }
}