package lotto.domain.lotto;

public class InvalidLottoTicketException extends RuntimeException {
    InvalidLottoTicketException(String message) {
        super(message);
    }
}
