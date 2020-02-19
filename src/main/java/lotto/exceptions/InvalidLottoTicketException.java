package lotto.exceptions;

public class InvalidLottoTicketException extends RuntimeException {
    public InvalidLottoTicketException() {
        super("유효하지 않은 당첨 번호 값입니다.");
    }
}
