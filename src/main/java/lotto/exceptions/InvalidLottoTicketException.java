package lotto.exceptions;

public class InvalidLottoTicketException extends RuntimeException {
    public InvalidLottoTicketException(String message) {
        super(message);
    }
}
