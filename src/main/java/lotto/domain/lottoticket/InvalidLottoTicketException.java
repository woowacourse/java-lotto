package lotto.domain.lottoticket;

public class InvalidLottoTicketException extends IllegalArgumentException {
    public InvalidLottoTicketException(String s) {
        super(s);
    }
}
