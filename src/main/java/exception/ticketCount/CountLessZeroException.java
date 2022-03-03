package exception.ticketCount;

public class CountLessZeroException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "로또티켓 수는 1 이상이어야 합니다.";

    public CountLessZeroException() {
        super(ERROR_MESSAGE);
    }
}
