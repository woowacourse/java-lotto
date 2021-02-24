package lotto.domain.exception;

public class InvalidManualTicketsSizeException extends RuntimeException {
    public InvalidManualTicketsSizeException() {
        super("수동구매 티켓 개수는 0개 이상, 총 구매 티켓 개수 이하여야 합니다.");
    }
}
