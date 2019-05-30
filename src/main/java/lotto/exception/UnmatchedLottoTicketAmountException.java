package lotto.exception;

public class UnmatchedLottoTicketAmountException extends IllegalArgumentException {
    public UnmatchedLottoTicketAmountException() {
        super();
    }

    public UnmatchedLottoTicketAmountException(String s) {
        super(s);
    }
}
