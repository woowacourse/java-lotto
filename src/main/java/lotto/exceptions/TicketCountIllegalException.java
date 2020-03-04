package lotto.exceptions;

public class TicketCountIllegalException extends IllegalArgumentException {
	private static String MESSAGE = "티켓 매수가 유효하지 않습니다.\n" +
			" - 티켓 매수는 0 이상의 정수여야합니다.\n" +
			" - 수동으로 구매할 티켓 매수는 전체 티켓 매수를 초과할 수 없습니다.";

	public TicketCountIllegalException() {
		super(MESSAGE);
	}
}
