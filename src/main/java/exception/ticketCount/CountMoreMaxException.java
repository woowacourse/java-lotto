package exception.ticketCount;

public class CountMoreMaxException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "수동로또 수가 구매한 로또 수를 초과할 수 없습니다.";

    public CountMoreMaxException() {
        super(ERROR_MESSAGE);
    }
}
