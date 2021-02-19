package lotto.exception;

public class LowBalanceException extends RuntimeException {

    private static final String ERROR_MESSAGE = "[ERROR] 구입하려는 티켓의 수보다 지불한 금액이 적습니다.";

    public LowBalanceException() {
        super(ERROR_MESSAGE);
    }
}
