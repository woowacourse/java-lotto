package lotto.exception;

public class NumberScopeException extends RuntimeException{
    public static final String ERROR_MESSAGE = "당첨 번호는 1 ~ 45 사이의 수여야만 합니다.";

    public NumberScopeException() {
        super(ERROR_MESSAGE);
    }
}
