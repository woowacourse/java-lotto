package exception;

public class InsufficientMoneyException extends RuntimeException {
    private static final String message = "구매할 돈이 부족합니다";

    public InsufficientMoneyException() {
        super(message);
    }
}
