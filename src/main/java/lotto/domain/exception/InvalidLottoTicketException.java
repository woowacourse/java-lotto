package lotto.domain.exception;

public class InvalidLottoTicketException extends IllegalArgumentException {
    public InvalidLottoTicketException(String s) {
        super(s);
    }
}
