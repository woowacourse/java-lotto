package lotto.domain;

public class InvalidLottoTicketException extends IllegalArgumentException {
    public InvalidLottoTicketException(String s) {
        super(s);
    }
}
