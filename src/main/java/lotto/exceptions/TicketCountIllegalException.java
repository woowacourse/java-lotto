package lotto.exceptions;

public class TicketCountIllegalException extends IllegalArgumentException {
	private static String MESSAGE = "구매한 금액 %d로 %d개의 티켓을 구매할 수 없습니다.\n" +
			" - 티켓 갯수는 0 이상의 정수여야합니다.\n" +
			" - 구매한 금액으로는 최대 %d개의 티켓을 구매하실 수 있습니다.";

	public TicketCountIllegalException(int purchaseMoney,
									   int illegalTicketNumber,
									   int maximumTicketNumber) {
		super(String.format(MESSAGE, purchaseMoney, illegalTicketNumber, maximumTicketNumber));
	}
}
