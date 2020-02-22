package lotto.domain.lottoTicket;

public class InvalidLottoTicketException extends IllegalArgumentException {
	public static final String NULL_OR_EMPTY = "null 또는 빈 리스트가 입력되었습니다.\n";
	public static final String DUPLICATION = "중복된 로또 번호를 입력하였습니다.\n";
	public static final String WRONG_SIZE = "로또 번호의 개수가 올바르지 않습니다.\n";

	public InvalidLottoTicketException(String s) {
		super(s);
	}
}
