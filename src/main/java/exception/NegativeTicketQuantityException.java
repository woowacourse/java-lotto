package exception;

public class NegativeTicketQuantityException extends RuntimeException {
    private static final String message = "티켓의 수에는 음수가 올 수 없습니다: ";

    public NegativeTicketQuantityException(final int size) {
        super(message + size);
    }
}
