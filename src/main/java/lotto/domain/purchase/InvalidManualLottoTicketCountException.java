package lotto.domain.purchase;

public class InvalidManualLottoTicketCountException extends IllegalArgumentException {
	public static final String NOT_INTEGER = "입력이 숫자가 아닙니다.";
	public static final String NEGATIVE = "0보다 작은 구매 개수는 입력이 불가능합니다.";
	public static final String NOT_PURCHASABLE = "지불한 금액으로 구매 가능한 개수를 초과합니다.";

	public InvalidManualLottoTicketCountException(String s) {
		super(s);
	}
}
