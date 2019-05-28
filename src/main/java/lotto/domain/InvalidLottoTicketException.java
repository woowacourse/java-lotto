package lotto.domain;

public class InvalidLottoTicketException extends RuntimeException {
    InvalidLottoTicketException(String message) {
        super(message);
    }
}
