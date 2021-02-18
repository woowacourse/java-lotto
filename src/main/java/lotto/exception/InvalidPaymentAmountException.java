package lotto.exception;

public class InvalidPaymentAmountException extends RuntimeException {

    private static final String ERROR_MESSAGE = "[ERROR] 1000원 미만의 금액은 입력할 수 없습니다.";

    public InvalidPaymentAmountException() {
        super(ERROR_MESSAGE);
    }
}
